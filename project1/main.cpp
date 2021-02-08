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

    string printNode(listNode* node){
        //(node's data, data of node's next) ->
        string nodeStr ="";
        if(node->next == NULL){
            nodeStr+="("+ to_string(node->val)+"," + "NULL ) ->";
        }else{
            nodeStr+="("+ to_string(node->val)+"," + to_string(node->next->val)+") ->";
        }
        
        

        return nodeStr;
    }
};

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
    int pop(){
        //if stack is empty
        listNode* temp = new listNode(-9999);
        temp = top->next;
        if(isEmpty()){
            return -9999;
        }else{
            top->next = top->next->next;
            size--;
        }

        return temp->val;
    }
    
    //need to finish
    void printStack(ofstream &outputFile, LLstack* stk){
        listNode* temp = stk->top;
        outputFile << "Top ->";
        while(temp->next != NULL){
            outputFile << temp->printNode(temp);
            temp = temp->next;
        }

       outputFile << "NULL" <<  endl;
         
    }
    //change junk = stk->pop()
    void buildStack(ifstream &inFile, ofstream &outFile){
        LLstack* stk = new LLstack();
        listNode* junk = NULL;
        string operation;
        int num;
        while(!inFile.eof()){
            
            inFile >> operation >> num;
            if(operation == "+"){
                stk->push(new listNode(num));
                outFile << "Added " << num << endl;
            }else{
                junk = new listNode(num);
                outFile << "Removed" << num << endl;
            }
            
            printStack(outFile,stk);
            
        }


       
    }
};

int main(int argc, char* argv[]){

    ifstream input;
    ofstream output;
    
    
    if(input.fail()){
        cout<< "File failed to open, make sure file name is typed correctly"<<endl;
        exit(1);
    }
    
   input.open(argv[1]);
   output.open(argv[2]);

   LLstack* stk = new LLstack();
   stk->buildStack(input,output);


    input.close();
    output.close();
    

}