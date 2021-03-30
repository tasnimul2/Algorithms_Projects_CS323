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
            for(int r = 0; r < power2size; r++){
                for(int c = 0; c < power2size; c++){
                    inFile >> data;
                    imgAry[r][c] = data;
                }
            }
        }
    }
    // set the given 2D array to zero
    void zero2DAry(){
        for(int i = 0 ; i < power2size; i++){
            for(int j = 0; j < power2size; j++){
                imgAry[i][j] = 0;
            }
        }
    }

    int** getImgAry(){
        return imgAry;
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
    for(int i = 0 ; i < myimg->power2size; i++){
        for(int j = 0; j < myimg->power2size; j++){
            output2 << arr[i][j] << " ";
        }
        output2 << endl;
    }

    //Need to do step 6 to 9.
    
    

}
