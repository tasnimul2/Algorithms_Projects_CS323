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