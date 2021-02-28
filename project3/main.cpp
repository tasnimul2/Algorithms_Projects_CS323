#include <iostream>
#include <fstream>
#include <string>
using namespace std;

class listNode{
    public:
    int val;
    listNode* next;

    //empty constructor
    listNode(){}
    //if a node object is created, set the nodes value to data and have its next node point to null
    listNode(int data){
        val = data;
        next = NULL;
    }

    //prints node
    void printNode(ofstream &output){
        //(node's data, data of node's next) ->
        if(next == NULL){
            output << "(" << val << ", NULL)-->";
        }else{
            output << "(" << val << ","<< next->val << ")-->";
        }
        
    }
};

//--------------------- LLStack -------------------------//
class LLstack{
    private:
    int size;
    public:

    listNode* top;

    // creates a new stack with a dummy node, set the data in
    //the dummy node to -9999 and set next to null; and let top points to the dummy node
    LLstack(){
        top = new listNode(-9999);
        top->next = NULL;
        size = 0;
    }

    //creates a stack by reading inputs from the input file
    void buildStack(ifstream &inFile, ofstream &outFile){
        LLstack* stk = new LLstack();
        listNode* junk = NULL;
        string operation;
        int num;
        
        //while not at end of file (eof) real file input line by line
        while(!inFile.eof()){
            
            inFile >> operation >> num;
            if(operation == "+" || operation != "-" ){ //operation != "-" is used to combat the initial white space ifstream reads
                stk->push(new listNode(num));
                outFile << "Pushed: " << num << endl;
            }else if(operation == "-") {
                junk = stk->pop();
                outFile << "Popped: " << junk->val << endl;
            }
            
            printStack(outFile,stk);
            
        }
    }
    // puts newNode at the top of Stack, after the dummy node.
    void push(listNode* node){
        node->next = top->next;
        top->next = node;
        size++;
    }
    // if stack is empty returns null, otherwise deletes and returns the top node of Stack.
    listNode* pop(){
        //if stack is empty
        listNode* temp = new listNode(-9999);
        temp = top->next;
        if(isEmpty()){
            return NULL;
        }else{
            top->next = top->next->next;
            size--;
        }

        return temp;
    }
    //checks if stack is empty
    bool isEmpty(){
        if(size==0){
            return true;
        }

        return false;
    }

    //prints the stack
    void printStack(ofstream &outputFile, LLstack* stk){
        listNode* temp = stk->top;
        outputFile << "Top -->";
        while(temp->next != NULL){
            //temp->printNode(temp,outputFile);
            temp->printNode(outputFile);
            temp = temp->next;
        }
        outputFile << "(" << temp->val << ",NULL)-->NULL"<< endl;       
    }
};

//-------------------------- LLQueue ---------------------------//

class LLqueue{
    private:
    int size;
    public:
    listNode* head;// head always points to the dummy node!
    listNode* tail;// tail always points to the last node of the queue; tail points to dummy initially.

    // creates a new Queue with a dummy node, set the data in the dummy node to -9999 and sets next to null;
    //let both head and tail point to the dummy node.
    LLqueue(){
        listNode* dummy = new listNode(-9999);
        dummy->next = NULL;
        head = dummy;
        tail= dummy;
        size = 0;
    }

    void buildQueue(ifstream &inFile, ofstream &outFile){
        LLqueue*  que = new LLqueue();
        listNode* junk = NULL;
        string operation;
        int num;

        while(!inFile.eof()){
            inFile >> operation >> num;
            if(operation =="+" || operation !="-"){
                que->insertQ(new listNode(num));
                outFile<<"Added: " << num << endl;
            }else if(operation == "-"){
                junk = que->deleteQ();
                outFile <<"Deleted: " << junk->val << endl;
            }

            printQ(0,outFile,que);

        }

    }

    //head is always dummy. head's next is 1st one out. tail is last one out.
    // insert the newNode after the tail of Q.
    void insertQ(listNode* node){
        listNode* temp = head;
        while(head->next != NULL){
            head = head->next;
        }
        head->next = node;
        tail = node;
        head = temp;
        size++;
    }

    // if Q is not empty, deletes the first node, after the dummy from Q and returns the
    // deleted node, otherwise, returns null.
    listNode* deleteQ(){
        listNode* temp = new listNode(-9999);
        temp = head->next;
        if(isEmpty()){
            return NULL;
        }else{
            head->next = head->next->next;
            size--;
        }

        return temp;
    }
    // checks to see if Q is empty, i.e., tail points to the dummy node. It returns true is Q is
    //empty and false otherwise.
    bool isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    // DOES NOT delete nodes in Queue! Outputs to outFile2, the entire Q, including the dummy
    //node, you may call printNode (...) using the following format:
    //MAKE MODIFICATIONS TO THIS METHOD
    void printQ(int whichtable,ofstream &outFile2, LLqueue* que){
        listNode* temp = que->head;
        outFile2 << "Top -->";
        while(temp->next != NULL){
            
            //temp->printNode(temp,outFile);
            temp->printNode(outFile2);
            temp = temp->next;
        }

       outFile2 << "(" << temp->val << ",NULL)-->NULL"<< endl;
    }

};

class RadixSort{
    public:
    int tableSize = 10;
    LLqueue* hashTable[2][10];
    int data;
    int currentTable; // either 0 or 1
    int previousTable; // either 0 or 1
    //set numDigits as the numbers are being inputted from the file
    int numDigits; // the number of digit in the largest integer that controls the number of iterations of Radix sort
    int offSet; // the absolute value of the largest negative integer in the data;
                // the offSet will add to each data before radix sort and subtract afterward.
    int currentPosition; // The digit position of the number while sorting.

    // Creates hashTable[2][tableSize].
    // Use loops to create LLQueue for each hashTable[i][j], i = 0 to 1 and j = 0 to 9, where
    // each hashTable[i][j] points to a dummy node and initially, head and tail point to dummy node.
    RadixSort(){
        for(int row = 0; row < 2;row++){
            for(int col = 0; col < 10; col++){
                hashTable[row][col] = new LLqueue();
            }
        }

    }//end of constructor 

    // Read from input file; determine the largest and smallest integers in the file
    // and establishes offset.
    void firstReading (){
    }

    // Constructs a linked list stack from the data in inFile.
    void loadStack (){
    }

    // Performs Radix sort; sorts from right-to-left.
    void RSort (){

    }
    // Moves all nodes on the stack to the first hash table.
    void moveStack(){

    }
    // Determines and returns the length of a given data.
    //suggestion: convert data to string to get the length.
    int getLength (int data){

        return 0;   
    }
    //Determines and returns the digit at the position of data. On your own!
    //** suggestion: convert data to string to get the digit then convert digit back to int.
    //** Reminding: string indexing is from left to right, when converting to string, the digit you want is
    // at the position of the string counting from right.
    int getDigit (int data,int position){

        return 0;
    }

    // Call printQueue () for each none empty queue in hashTable[whichTable].
    void printTable (int whichTable,ofstream &outFile2){

    }
    // Print each none empty queue in hashTable[whichTable], one data per text line;
    //*** make sure to subtract offSet before printing the data.
    void printSortedData (int whichTable,ofstream &outFile1){
        
    }


    

};



int main(int argc,char* argv[]){
    ifstream input;
    input.open(argv[1]);
    if(input.fail()){
        cout<< "error opening input file, make sure the name of file is typed correctly"<<endl;
    }
    int num;
    char blank;
    input>>blank>>blank>>blank;

    while(!input.eof()){
        input >> num;
        cout << num << endl;
    }

    RadixSort* srt = new RadixSort();
    for(int r = 0; r < 2;r++){
        for(int c = 0; c<10; c++){
            cout << srt->hashTable[r][c]->head->val<<" ";
        }
        cout<<endl;
    }
}