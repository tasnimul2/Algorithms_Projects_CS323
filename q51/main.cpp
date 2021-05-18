#include <iostream>
#include <fstream>
using namespace std;

class uEdge{
    public: 
    int Ni; // an integer 1 to N
    int Nj; // an integer 1 to N
    int cost; // a positive integer > 0
    uEdge* next;

    // to create a new uEdge node with (Ni, Nj , cost, null)
    uEdge(int i, int j, int cst, uEdge* nxt){
        Ni = i;
        Nj = j;
        cost = cst;
        next = nxt;
    }
    // print edge info to deBugFile
    // /<0, 0, 0, Ni1> -> <Ni1, Nj1, edgeCost, Ni2> -> < Ni2, Nj2, edgeCost, Ni3> ...
    void printEdge (ofstream& deBugFile){
        if(next != nullptr){
            deBugFile << "<" << Ni << ", " << Nj << ", " << cost <<", " << next->Ni <<"> -> ";
        }else{
            deBugFile << "<" << Ni << ", " << Nj << ", " << cost <<", " << "null" <<"> -> ";
        }
        
    }


};

class kruskalMST{
    public:
    int numNodes; //number of nodes in G.
    int nodeInSetA; // get from argv[2]
    int* whichSet;
    uEdge* edgelistHead = new uEdge(0,0,0,NULL);
    uEdge* MSTlistHead = new uEdge(0,0,0,NULL);
    int totalMSTCost = 0;
    int listSize = 0;

    kruskalMST(int numNode){
        numNodes = numNode;
        whichSet = new int[numNodes+1];
        for(int i = 0; i < numNodes + 1 ; i++){
            whichSet[i] = 2;
        }
    }

     // inserts edge into the list of edgelistHead in ascending order with respect to edge cost.
    // Re-use code in your previous projects.
    void listInsert(uEdge* edge){
        uEdge* curr = edgelistHead;
        uEdge* temp = edgelistHead;
        bool inserted = false;
        if(isEmpty()){
            curr->next = edge;
            listSize++;
        }else{
            while(curr->next != NULL){
                if(edge->cost < curr->next->cost){
                    uEdge* tmp2 = curr->next;
                    curr->next = edge;
                    edge->next = tmp2;
                    listSize++;
                    inserted = true;
                    break;
                }
                curr = curr->next;
            }
            if(!inserted){
                curr->next = edge;
                inserted = true;
            }
            
        }
        curr = temp;
    }

    // The removeEdge method searches edge nodes in edgelistHead linked list
    // to find the first edge, e, where: a) whichSet[e->Ni] != whichSet[e->Nj]
    // && b) either whichSet[e->Ni] == 1 or whichSet[e->Nj] == 1.
    // Then, remove edge node e from the list and returns e.
    // Re-use code in your previous projects.
    uEdge* removeEdge(){
        uEdge* curr = edgelistHead;
        uEdge* temp = edgelistHead;
        uEdge* removedEdge;
        while( curr->next != NULL){
            if((whichSet[curr->next->Ni] != whichSet[curr->next->Nj]) && (whichSet[curr->next->Ni] == 1 || whichSet[curr->next->Nj] == 1  )){
                removedEdge = curr->next;
                curr->next = curr->next->next;
                //curr->next->next = nullptr;
                listSize--;
                return removedEdge;
            }
            curr = curr->next;
        }

        return new uEdge(0,0,0,nullptr);
    }
    
    // add edge on the top of MSTlistHead after dummy. NOT sorted!
    // Re-use code in your previous projects.
    void addEdge (uEdge* edge){
        uEdge* temp = MSTlistHead;
        edge->next = MSTlistHead->next;
        MSTlistHead->next = edge;
        MSTlistHead = temp;
        listSize++;
    }
    // print the whichSet array to deBugFile. Re-use code in your previous projects.
    void printSet(ofstream&  debugFile){
        debugFile  << endl;
        debugFile << "----PRINTING WHICHSET ARRAY----" << endl;
        debugFile << "[ " ;
        for(int i = 0; i < numNodes + 1 ; i++){
            debugFile << whichSet[i] << ", ";
        }
        debugFile << "] " << endl;
    }
    // print nodes in the list point by edgelistHead to deBugFile with the following
    // edgelistHead -> <0, 0, 0, Ni1> -> <Ni1, Nj1, edgeCost, Ni2> -> < Ni2, Nj2, edgeCost, Ni3> ...
    // Re-use code in your previous projects.
    void printEdgeList(ofstream& debugFile){
        debugFile << "---- PRINTING EDGELIST ---- " << endl;
        uEdge* curr = edgelistHead;
        uEdge* temp = edgelistHead;
        debugFile << "edgelistHead -> ";
        while(curr != NULL){
            curr->printEdge(debugFile);
            curr = curr->next;
        }
        debugFile <<endl;
    }

    // print nodes in the list point by MSTlistHead to outFile with the following
    // MSTlistHead -> <0, 0, 0, Ni1> -> <Ni1, Nj1, edgeCost, Ni2> -> < Ni2, Nj2, edgeCost, Ni3> ...
    // Re-use code in your previous projects.
    void printMSTList (ofstream& outFile){
        outFile << "---- PRINTING MSTLIST ---- " << endl;
        uEdge* curr = MSTlistHead;
        uEdge* temp = MSTlistHead;
        outFile << "MSTlistHead -> ";
        while(curr != NULL){
            curr->printEdge(outFile);
            curr = curr->next;
        }
        outFile << endl;
    }

    void initSets(){
        for(int i = 0; i < numNodes ; i++){
            whichSet[i] = i;
        }
    }

    void merge2Sets(uEdge* edge){
        int x = whichSet[edge->Ni];
        int y = whichSet[edge->Nj];

        for(int i =0; i<= numNodes; i++){
            if(whichSet[i] == x || whichSet[i] == y){
                if(x<y){
                    whichSet[i] =x;
                }else{
                    whichSet[i] = y;
                }
            }
        }
    }

    bool oneSet(){
        for(int i = 1; i < numNodes;i++){
            if(whichSet[i] != whichSet[i+1]){
                return false;
            }
        }
        return true;
    }

     bool isEmpty(){
        return listSize == 0;
    }

    bool isDummy(uEdge* e){
        return (e->cost == 0 && e->Ni == 0 && e->Nj ==0 && e->next ==nullptr);
    }
};

int main(int argc, char* argv[]){
    ifstream inFile;
    ofstream mstFile,deBugFile;
    inFile.open(argv[1]);
    if(inFile.fail()){
        cout << "File failed to open, make sure input file name is typed correctly"<<endl;
        exit(1);
    }
    mstFile.open(argv[2]);
    deBugFile.open(argv[3]);
    int numNodes;
    inFile>> numNodes;

    kruskalMST* mst = new kruskalMST(numNodes + 1);
    mst->initSets();
    mst->printSet(deBugFile);

    int ni,nj,edgeCost;
    while(!inFile.eof()){
        inFile >> ni >> nj >> edgeCost;
        uEdge* newEdge = new uEdge(ni,nj,edgeCost,nullptr);
        mst->listInsert(newEdge);
    }
    mst->printEdgeList(deBugFile);


    deBugFile << endl;
    deBugFile << " REMOVING EDGES " << endl;
    while(!mst->oneSet()){
        uEdge* newEdge = mst->removeEdge();
        newEdge->printEdge(deBugFile);
        mst->printEdgeList(deBugFile);
        mst->addEdge(newEdge);
        mst->totalMSTCost+= newEdge->cost;
        mst->merge2Sets(newEdge);
        mst->printSet(deBugFile);
        mst->printEdgeList(deBugFile);
        mst->printMSTList(deBugFile);
    }

    mstFile<< "*** Kruskal's MST of the input graph, G is: ***" << endl;
    mstFile << "num nodes: " << numNodes << endl;
    mst->printMSTList(mstFile);
    mstFile<< " *** MST total cost = " << mst->totalMSTCost << endl;

    /*
    int n ;
    while(!inFile.eof()){
        inFile >> n;
        cout << n << " "; 
    }
    */
   deBugFile.close();
   mstFile.close();
}