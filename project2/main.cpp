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
            outFile << "(" <<firstName << " " << lastName << " " << next->firstName << ")-->";
        }else{
            outFile << "(" <<firstName << " " << lastName << " " << "NULL" << ")-->NULL";
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
    void informationProcessing(ifstream &inFile,ofstream &outFile2){
        char openingBlankSpot;
        char op;
        string firstName,lastName;
        int index;
        //ifream add three black characters at the start of the document, this helps fix it. 
        inFile>>openingBlankSpot >> openingBlankSpot >>openingBlankSpot;
        while(!inFile.eof()){
            inFile >> op >> firstName >> lastName;
            //cout << op << " "<< firstName << " " <<lastName <<endl;
            outFile2 << op << " " << firstName << " " <<lastName <<" is being processed" << endl;
            index = doIt(lastName);
            outFile2 << firstName << " " <<lastName <<" is processing in Hash Table " << index << endl;
            printList(index,outFile2);

            if(op == '+'){
                hashInsert(index,firstName,lastName,outFile2);
            }else if(op == '-'){
                hashDelete(index,firstName,lastName,outFile2);
            }else if(op == '?'){
                hashRetrieval(index,firstName,lastName,outFile2);
            }
        }
    } 

    // search thru hashTable[index] linked list to locate the
    //record with firstName and lastName. SEE EMAIL FOR ALGORITHM.
    //returns the previous node of the matching node.
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
        outFile2 << "***Performing hash insert on " << firstName << " " << lastName << endl;
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
    void hashDelete(int index, string firstName, string lastName,ofstream &outFile2){
        outFile2<<"*** Performing delete on " << firstName << " " << lastName << endl;
        listNode* spot = findSpot(index,firstName,lastName);
        //if (Spot's next != null *and* Spot’s next’s lastName == lastName *and* Spot’s next’s firstName == firstName)
        if(spot->next != NULL && spot->next->lastName == lastName && spot->next->firstName == firstName){
            listNode* junk = spot->next;
            spot->next = spot->next->next;
            junk->next = NULL;
            free(junk);
            printList(index,outFile2);
        }else{
            outFile2<< "***Warning ,the record is *not* in the database!";
        }
    }

    //see alogrithm
    void hashRetrieval(int index,string firstName,string lastName,ofstream &outFile2){
        outFile2<<"*** Performing hashRetrieval on "<<firstName << " "<<lastName << endl;
        listNode* spot = findSpot(index,firstName,lastName);
        //if Spot's next != null *and* Spot’s next’s lastName == lastName *and* Spot’s next’s firstName == firstName
        if(spot->next != NULL && spot->next->lastName == lastName && spot->next->firstName==firstName){
            outFile2<<"Yes, the record is in the database!" <<endl;
        }else{
            outFile2<<"No, the record is not in the database!" <<endl;
        }
    }
    // print the linked list of hashTable [index], use the format given in the above.
    void printList (int index, ofstream &outFile){
        listNode* head = &hashTbl[index];
        listNode* temp = head;
        outFile<<"HashTable["<<index<<"]: ";
        while(head != NULL){
            head->printNode(outFile);
            head = head->next;
        }
        outFile<<endl;
        head = temp;
    }
    // output the entire hashTable, call printList (...), index from 0 to bucketSize -1.
    void printHashTable (ofstream &outFile){

        for(int i = 0; i < bucketSize ;i++){
            printList(i,outFile);
        }
    }
};

int main(int argc, char* argv[]){
    ifstream input;
    ofstream output1,output2;
    int size = 0;

    input.open(argv[1]);

    if(input.fail()){
        cout<< "File failed to open, make sure input file name is typed correctly"<<endl;
        exit(1);
    }

    string temp = argv[2];
    size = stoi(temp);
    output1.open(argv[3]);
    output2.open(argv[4]);


   hashTable* table = new hashTable(size);
   listNode node;
   table->createHashTable();
   table->informationProcessing(input,output2);
   table->printHashTable(output1);
   
   


    input.close();
    output1.close();
    output2.close();
}