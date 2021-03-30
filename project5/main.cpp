#include <iostream>
#include <fstream>

using namespace std;

class Image{
    public:
    int numRows;
    int numCols;
    int minVal;
    int maxVal;

    int power2size;
    int **imgAry ; //a 2D array, need to dynamically allocate at run time of size power2Size by power2Size.
    
    Image(){}

    Image(int row, int col, int min, int max, ofstream &outFile2){
        power2size = computePower2(row,col);
        outFile2 << "power2size: " << power2size << endl;
        imgAry = new int*[power2size];
        numRows = row;
        numCols = col;
        minVal = min;
        maxVal = max;

        for(int i = 0; i < power2size; i++){
            imgAry[i] = new int[power2size];
        }
        
    }
    // The method determines the smallest power of 2
    // that fits the input image. It returns the
    // the size (one side) of the power2Size
    int computePower2(int numRows, int numCols){
        int size = max(numRows,numCols);
        int power2 = 2;
        while(size > power2){
            power2*=2;
        }
        return power2;
    }

    //load the input data onto imgAry, begins at (0, 0)
    void loadImage (ifstream &inFile) {
        int data;
        while(!inFile.eof()){
            for(int r = 0; r < numRows; r++){
                for(int c = 0; c < numCols; c++){
                    inFile >> data;
                    imgAry[r][c] = data;
                }
            }//end of outer for loop
        }//end of while loop
    }

    // set the given 2D array to zero
    void zero2DAry(){
        for(int i = 0 ; i < power2size; i++){
            for(int j = 0; j < power2size; j++){
                imgAry[i][j] = 0;
            }
        }
    }
    
    //return the imgArray
    int** getImgAry(){
        return imgAry;
    }
};

class QtTreeNode{
    public:
    int color = -1; //0 1 or 5
    int upperR = 0; // the row coordinate of the upper corner of the image area in which this node is representing
    int upperC = 0; // the column coordinate of the upper corner of the image area in which this node is representing
    int size = 0;

    QtTreeNode* NWkid = NULL;
    QtTreeNode* NEkid = NULL;
    QtTreeNode* SWkid = NULL;
    QtTreeNode* SEkid = NULL;
    QtTreeNode(){};
    QtTreeNode(int upR, int upC,int sze,QtTreeNode* Nwkid,QtTreeNode* Nekid,QtTreeNode* Swkid, QtTreeNode*Sekid){
        upperR = upR;
        upperC = upC;
        size = sze;
        NWkid = Nwkid;
        NEkid = Nekid;
        SWkid = Swkid;
        SEkid = Sekid;
    }
    // output the given node’s: color, upperR, upperC, NWkid’s color, NEkid’s color,
    //SWkid’s color, SEkid’s color), one node per text line
    void printQtNode(ofstream &outFile2){
        string nw = "";
        string ne = "";
        string sw = "";
        string se = "";
     
        if(NEkid == NULL){
            ne = "NULL";
        }else{
            ne = to_string(NEkid->color);
        }
        if(NWkid == NULL){
            nw = "NULL";
        }else{
            nw = to_string(NWkid->color);
        }
        if(SWkid == NULL){
            sw = "NULL";
        }else{
            sw = to_string(SWkid->color);
        }
        if(SEkid == NULL){
            se = "NULL";
        }else{
            se = to_string(SEkid->color);
        }
        //outFile2 << ",NW:"<< nw<< ",NE:" << ne << ",SW:" << sw << ",SE:" << se << ")"  << endl;
        outFile2 << "(Color:" << this->color << ",UR:" << this->upperR << ",UC:" << this->upperC << ",NW:"
            << nw << ",NE:" << ne << ",SW:" << sw << ",SE:" << se << ")"<<endl;
    }

};

class QuadTree {
    public:
    QtTreeNode* QtRoot;
    //algorithm given
    QtTreeNode* buildQuadTree(int** arr,int upR, int upC, int size, ofstream &outFile2){
        QtTreeNode* node = new QtTreeNode(upR,upC,size,NULL,NULL,NULL,NULL);
        outFile2 << "New Node: ";
        node->printQtNode(outFile2);

        if(size == 1){
            node->color = arr[upR][upC];
        }else{
            int halfSize = size/2;
            node->NWkid = buildQuadTree(arr,upR,upC,halfSize,outFile2);
            node->NEkid = buildQuadTree (arr, upR, upC + halfSize, halfSize,outFile2);
            node->SWkid = buildQuadTree (arr, upR + halfSize, upC, halfSize,outFile2);
            node->SEkid = buildQuadTree (arr, upR + halfSize, upC + halfSize, halfSize,outFile2);
            int sumColors = node->NWkid->color + node->NEkid->color + node->SWkid->color + node->SEkid->color;

            if(sumColors == 0){
                node->color = 0;
                node->NWkid = NULL;
                node->NEkid = NULL;
                node->SWkid = NULL;
                node->SEkid = NULL;
            }else if (sumColors == 4){
                node->color = 1;
                node->NWkid = NULL;
                node->NEkid = NULL;
                node->SWkid = NULL;
                node->SEkid = NULL;
            }else{
                node->color = 5;
            }
        }
        return node;
    }
    // returns true if node is a quadtree leaf node, otherwise returns false.
    bool isLeaf (QtTreeNode* node){

        if(node->NEkid == NULL && node->NWkid == NULL && node->SWkid == NULL && node->SEkid == NULL){
            return true;
        }
        return false;
    }
    //algorithms given
    void preOrder(){

    }

    void postOrder(){

    }
};

int main(int argc, char* argv[]){
    ifstream input;
    ofstream output1, output2;
    int numRows, numCols, minVal, maxVal;
    char dummy;

    input.open(argv[1]);
    output1.open(argv[2]);
    output2.open(argv[3]);

    if(input.fail()){
        cout<< "error opening input file, make sure the name of file is typed correctly"<<endl;
    }
    //on linux, three dummy charcters are read 
    input >> dummy >> dummy >>dummy;//comment out this line on windows. (DO NOT comment out if on Mac or Linux)
    input >> numRows >> numCols >>  minVal >>  maxVal;
    

    Image *myimg = new Image(numRows,numCols,minVal,maxVal,output2);
    //the constructor takes care of computePower2(numRows, numCols)
    //and dynamically allocates the array size of power2Size by power2Size
    myimg->zero2DAry();
    myimg->loadImage(input);
    int **arr = myimg->getImgAry();

    //output imgAry to outFile2 with caption
    output2 <<"Image after power2size x power2size dimenstions adjustment" << endl;
    for(int i = 0 ; i < myimg->power2size; i++){
        for(int j = 0; j < myimg->power2size; j++){
            output2 << arr[i][j] << " ";
        }
        output2 << endl;
    }

    //Need to do step 6 to 9.

    /*
    QtTreeNode* nodea =  new QtTreeNode(0,1,1,NULL,NULL,NULL,NULL);
    //nodea->color = 1;
    QtTreeNode* node1 =  new QtTreeNode(0,1,1,nodea,NULL,NULL,nodea);
    node1->printQtNode(output2);
    nodea->printQtNode(output2);
    */

   QuadTree* qTree;
   qTree->QtRoot = qTree->buildQuadTree(myimg->getImgAry(),0,0,myimg->power2size,output2);
    

}
