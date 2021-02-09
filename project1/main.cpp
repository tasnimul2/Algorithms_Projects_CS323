#include <iostream>
#include<fstream>
#include<string>


using namespace std;


class listNode{
    public:
    int val;
    listNode* next = NULL;
    listNode(){

    }
    listNode(int data){
        val = data;
    }

    void printNode(listNode* node, ofstream &output){
        //(node's data, data of node's next) ->
        string nodeStr ="";
        if(node->next == NULL){
            //nodeStr+="("+ to_string(node->val)+"," + "NULL ) ->";
            output << "(" << node->val << ", NULL) ->";
        }else{
            //nodeStr+="("+ to_string(node->val)+"," + to_string(node->next->val)+") ->";
            output << "(" << node->val << ","<< node->next->val << ") ->";
        }
        
    }
};
//--------------------- LLStack -------------------------//
class LLstack{
    private:
    
    public:

    listNode* top;
    int size = 0;
    LLstack(){
        top = new listNode(-9999);
        top->next = NULL;
    }

    bool isEmpty(){
        if(size==0){
            return true;
        }

        return false;
    }
    // puts newNode at the top of Stack, after the dummy node.
    void push(listNode* node){
        node->next = top->next;
        top->next = node;
        size++;
    }
    // if stack is empty returns null, otherwise deletes and returns the top node of Stack.
    //make return type listNode*
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
    
    //need to finish
    void printStack(ofstream &outputFile, LLstack* stk){
        listNode* temp = stk->top;
        outputFile << "Top ->";
        while(temp->next != NULL){
            //outputFile << temp->printNode(temp,outputFile);
            temp->printNode(temp,outputFile);
            temp = temp->next;
        }

       outputFile << "NULL" <<  endl;
         
    }

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
                outFile << "Added " << num << endl;
            }else if(operation == "-") {
                junk = stk->pop();
                outFile << "Removed " << junk->val << endl;
            }
            
            printStack(outFile,stk);
            
        }


       
    }
};
//-------------------------- LLQueue ---------------------------//

class LLqueue{
    private:
    int size = 0;
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
    }

    void buildQueue(ifstream &inFile, ofstream &outFile){

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
    void printQ(ofstream &outFile){

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

   LLqueue* que = new LLqueue();
   que->insertQ(new listNode(1));
   que->insertQ(new listNode(2));
   que->insertQ(new listNode(3));
   que->insertQ(new listNode(4));
   que->insertQ(new listNode(5));

   while(!que->isEmpty()){
       cout<< que->deleteQ()->val <<endl;
   }



    

}