//Mohammed Chowdhury
//CS 323-33

#include <iostream>
#include<fstream>
#include<string>
using namespace std;

class listNode{
    string firstName;
    string lastName;
    listNode* next = NULL;

    listNode(string fn, string ln){
        firstName = fn;
        lastName = ln;
    }

    void printNode(ofstream outFile){
        //(this node's firstName, this node’s lastName, next node’s firstName ) 
        outFile << "(" <<firstName << " " << lastName << " " << next->firstName << ") -->"<< endl;
    }
};

class hashTable{
    char op;
    int bucketSize = 0; //argv[2]
    listNode* hashTable[];

    //The method dynamically allocates hashTable [], size of bucketSize,
    //where each entry point to a dummy node: (“dummyfirst”, “dummylast”, null)
    // On your own! You should know how to do this.
    void createHashTable(){

    }

    // Given the lastName, the method returns the ‘index’ between 0 to bucketSize-1
    // The function can be found in the lecture note.
    int doIt(string lastName){


    }

    // see algorithm below.
    void informationProcessing(ifstream inFile,ofstream outFile){

    } 

    // search thru hashTable[index] linked list to locate the
    //record with firstName and lastName. See algorithm below.
    listNode* findSpot(int index, string firstName, string lastName){

    }

    //see algorithm
    void hashInsert(){

    }
    // see algorithm 
    void hashDelete(){

    }

    //see alogrithm
    void hashRetrieval(){

    }
    // print the linked list of hashTable [index], use the format given in the above.
    void printList (int index, ofstream outFile){

    }
    // output the entire hashTable, call printList (...), index from 0 to bucketSize -1.
    void printHashTable (ofstream outFile){
    }
};

int main(int argc, char* argv[]){
    cout<<"hello" << endl;
    ifstream input;

    input.open(argv[1]);

    if(input.fail()){
        cout<< "File failed to open, make sure input file name is typed correctly"<<endl;
        exit(1);
    }
    string  first,  last;
    char op;

    while(!input.eof()){
        input >> op>> first >> last;

        cout << op<< " "<< first <<  " " << last << endl;
    }


    input.close();
}