//Mohammed Chowdhury
//CS 323-33

#include <iostream>
#include<fstream>


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

            printQ(outFile,que);

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
    void printQ(ofstream &outFile, LLqueue* que){
        listNode* temp = que->head;
        outFile << "Top -->";
        while(temp->next != NULL){
            
            //temp->printNode(temp,outFile);
            temp->printNode(outFile);
            temp = temp->next;
        }

       outFile << "(" << temp->val << ",NULL)-->NULL"<< endl;
    }

};

//--------------------- LList Class----------------------------------//
class LList{
    private:
    int size;

    public:
    listNode* listHead; // always points to the dummy node

    // create new list with a dummy node, set the data in the dummy node to -9999. and let
    //listHead points to the dummy node.
    LList(){
        listNode* dummy = new listNode(-9999);
        listHead = dummy;
        size = 0;
    }

    void buildList(ifstream &inFile, ofstream &outFile){
        LList*  list = new LList();
        listNode* junk = NULL;
        string operation;
        int num;

        while(!inFile.eof()){
            inFile >> operation >> num;
            if(operation =="+" || operation !="-"){
                list->listInsert(new listNode(num));
                outFile<<"Added: " << num << endl;
            }else if(operation == "-"){
                junk = list->deleteOneNode(num);
                if(junk == NULL){
                    outFile <<"Sorry, " <<num<<" is not in the list (Cannot Remove)" << endl;
                }else{
                    outFile <<"Deleted: " << junk->val << endl;
                }          
            }
            printList(outFile,list);
        }
    }
    //algorithm used from class notes
    void listInsert(listNode* node){
        bool nodeInserted = false;
        listNode* temp = listHead;
        while(temp->next != NULL ){
            if(temp->val == node->val){
                node->next = temp->next;
                temp->next = node;
                size++;
                nodeInserted = true;
                break;
            }else if(temp->next->val > node->val){
                node->next = temp->next;
                temp->next = node;
                nodeInserted = true;
                break;
            }

            temp = temp->next;
        }
        if(!nodeInserted){
            node->next = temp->next;
            temp->next = node;
        }
        //listHead = temp;
    }

    // First, searches a node in the list that contains data; if such node exits,
    //deletes the node from the list and returns the deleted node, otherwise, returns null.
    listNode* deleteOneNode(int nodeData){
        listNode* junk = new listNode(-9999);
        listNode* temp = listHead;

        while(temp->next != NULL){
            if(temp->next->val == nodeData){
                temp->next = temp->next->next;
                junk = temp->next;
                size--;
                return junk; //end the loop so other nodes with the same value dont get removed. 
            }
            temp = temp->next;
        }
        
        junk = NULL;
        return junk;
    }
    // Output to outFile3, the entire list, including the dummy node, by calling pirntNode(...) until at the end of the list,
    //using the following format: listHead -->(-9999, next’s data1)--> ( data, next’s data)...... --> ( data, NULL)--> NULL
    void printList(ofstream &outFile, LList* list){
        listNode* temp = list->listHead;
        outFile << "listHead -->";
        while(temp->next != NULL){
            //temp->printNode(temp,outFile);
            temp->printNode(outFile);
            temp = temp->next;
        }

       outFile << "(" << temp->val << ",NULL)-->NULL"<< endl;
    }

    bool isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

};

int main(int argc, char* argv[]){

    ifstream input;
    ofstream output;

    input.open(argv[1]);
    if(input.fail()){
        cout<< "File failed to open, make sure input file name is typed correctly"<<endl;
        exit(1);
    }
    
    output.open(argv[2]);

   LLstack* stk = new LLstack();
   stk->buildStack(input,output);
   input.close();
   output.close();

   input.open(argv[1]);
   output.open(argv[3]);
   LLqueue* que = new LLqueue();

   que->buildQueue(input,output);
   input.close();
   output.close();


   input.open(argv[1]);
   output.open(argv[4]);

   LList* list = new LList();
   list->buildList(input,output);
   input.close();
   output.close();
}