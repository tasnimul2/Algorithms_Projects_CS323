//Mohammed Chowdhury
//CS 323-33

#include <iostream>
#include<fstream>
#include<string>
using namespace std;

class listNode{
    public:
    string firstName;
    string lastName;
    listNode* next = NULL;

    listNode(){
    }
    listNode(string fn, string ln){
        firstName = fn;
        lastName = ln;
    }

    void printNode(ofstream &outFile){
        //(this node's firstName, this node’s lastName, next node’s firstName ) 
        if(next != NULL){
            outFile << "(" <<firstName << " " << lastName << " " << next->firstName << ")-->"<< endl;
        }else{
            outFile << "(" <<firstName << " " << lastName << " " << "NULL" << ")-->"<< endl;
        }
        
    }
};

class hashTable{
    public:
    char op;
    int bucketSize = 0; //argv[2]
    listNode* hashTbl;

    hashTable(){

    }

    hashTable(int n){
        bucketSize = n;
    }


    //The method dynamically allocates hashTable [], size of bucketSize,
    //where each entry point to a dummy node: (“dummyfirst”, “dummylast”, null)
    // On your own! You should know how to do this.
    void createHashTable(){
        hashTbl = new listNode[bucketSize];
        listNode* dummyNode = new listNode("dummyFirst","dummyLast");
        for(int i =0; i < bucketSize; i++){
            hashTbl[i] = *dummyNode;
        }
        
    }

    // Given the lastName, the method returns the ‘index’ between 0 to bucketSize-1
    // The function can be found in the lecture note.
    int doIt(string lastName){
        unsigned long hash_value = 1; //unsgined keyword makes it so it's value never turns negative

        for(int i = 0; i < lastName.length();i++){
            hash_value = hash_value * 32 + (int)lastName[i];
        }
        return hash_value % bucketSize;
    }

    // see algorithm below. FINISH LAST
    void informationProcessing(ifstream &inFile,ofstream &outFile){

    } 

    // search thru hashTable[index] linked list to locate the
    //record with firstName and lastName. SEE EMAIL FOR ALGORITHM.
    listNode* findSpot(int index, string firstName, string lastName){
        listNode* spot = &hashTbl[index];
        //if Spot's next != null *and*  Spot's next's lastName < lastName
        //Spot =Spot's next
        while(spot->next != NULL && spot->next->lastName < lastName ){
            spot = spot->next;
        }
        //if Spot's next != null *and*  Spot's next's lastName == lastName *and* 
        //Spot's next's firstName < firstName , then Spot =Spot's next
        while(spot->next != NULL && spot->next->lastName == lastName && spot->next->firstName < firstName){
            spot = spot->next;

        }
        return spot;
    }

    //see algorithm
    void hashInsert(int index, string firstName,string lastName,ofstream &outFile2){
        outFile2 << "performing hash insert on " << firstName << " " << lastName << endl;
        listNode* spot = findSpot(index,firstName,lastName);

        if( spot->next != NULL && spot->next->lastName == lastName && spot->next->firstName == firstName){
            outFile2 << "Warning, the record is already in the database!" << endl;
        }else{
            listNode* node = new listNode(firstName,lastName);
            node->next = spot->next;
            spot->next = node;
            printList(index,outFile2);
        }
    }
    // see algorithm 
    void hashDelete(){
        
    }

    //see alogrithm
    void hashRetrieval(){

    }
    // print the linked list of hashTable [index], use the format given in the above.
    void printList (int index, ofstream &outFile){

    }
    // output the entire hashTable, call printList (...), index from 0 to bucketSize -1.
    void printHashTable (ofstream outFile){
    }
};

int main(int argc, char* argv[]){
    cout<<"hello" << endl;
    ifstream input;
    ofstream output1;
    int size = 0;

    input.open(argv[1]);

    if(input.fail()){
        cout<< "File failed to open, make sure input file name is typed correctly"<<endl;
        exit(1);
    }

    string temp = argv[2];
    size = stoi(temp);
    output1.open(argv[3]);


   hashTable* table = new hashTable(size);
   listNode node;
   table->createHashTable();
   
   
   for(int i = 0; i < size; i++){
     node  =  table->hashTbl[i];
     node.printNode(output1);
     
   }
   
   


    input.close();
}