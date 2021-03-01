#include <climits>
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
    int getSize(){
        return size;
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
        }
        top->next = top->next->next;
        temp->next= NULL;
        size--;
        
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
        tail->next = NULL;
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

            printQ(0,0,que,outFile);

        }

    }

    //head is always dummy. head's next is 1st one out. tail is last one out.
    // insert the newNode after the tail of Q.
    void insertQ(listNode* node){
       if(isEmpty()){
           head->next = node;
           tail = node;
           size++;
           
       }else{
           tail->next = node;
           tail = node;
           size++;
       }
    }

    // if Q is not empty, deletes the first node, after the dummy from Q and returns the
    // deleted node, otherwise, returns null.
    listNode* deleteQ(){
        listNode* temp = new listNode(-9999);
        temp = head->next;
        if(isEmpty()){
            return NULL;
        }
        head->next = head->next->next;
        temp->next = NULL;
        size--;
        

        return temp;
    }
    // checks to see if Q is empty, i.e., tail points to the dummy node. It returns true is Q is
    //empty and false otherwise.
    bool isEmpty(){
    
       if(size ==0){
           return true;
       }else{
           return false;
       }
    }

    // DOES NOT delete nodes in Queue! Outputs to outFile2, the entire Q, including the dummy
    //node, you may call printNode (...) using the following format:
    //MAKE MODIFICATIONS TO THIS METHOD
    void printQ(int whichtable,int index, LLqueue* que,ofstream &outFile2){
        listNode* temp = que->head;
        outFile2 << "Table[" << whichtable<<"]"<<"["<<index<<"]: ";
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
    int offset; // the absolute value of the largest negative integer in the data;
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
    void firstReading (ifstream &inFile, ofstream &outFile2){
        outFile2 << "*** Performing firstReading" <<endl;
        char blankSpaceOnLinux;
        int negativeNum =0;
        int positiveNum =0;
        int data;
        inFile>>blankSpaceOnLinux>>blankSpaceOnLinux>>blankSpaceOnLinux; //if on windows OS comment out this line please
        while(!inFile.eof()){
            inFile>>data;
            if(data < negativeNum){
                negativeNum = data;
            }else if(data > positiveNum){
                positiveNum = data;
            }
        }
        if(negativeNum < 0){
            offset = abs(negativeNum);
        }else{
            offset = 0;
        }
        positiveNum = positiveNum + offset;
        numDigits = getLength(positiveNum);
        outFile2 << "positiveNum: " << positiveNum <<endl;
        outFile2 << "negativeNum: " << negativeNum <<endl;
        outFile2 << "offset: " << offset <<endl;
        outFile2 << "numDigits: " << numDigits <<endl;
    }

    // Constructs a linked list stack from the data in inFile.
    LLstack* loadStack(ifstream &inFile, ofstream &outFile2){
        char blankSpaceOnLinux;
        int data;
        outFile2 << "*** Performing loadStack" << endl;
        LLstack* stk = new LLstack();
        inFile>>blankSpaceOnLinux>>blankSpaceOnLinux>>blankSpaceOnLinux; //if on windows OS comment out this line please
        while (!inFile.eof()){
            inFile >>data;
            data+= offset;
            listNode* node = new listNode(data);
            stk->push(node);
        }
       
        return stk;
    }

    // Performs Radix sort; sorts from right-to-left.
    void RSort(LLstack* stack, ofstream &outFile1, ofstream &outFile2){
        outFile2 << "*** Performing RSort" <<endl;
        currentPosition = 0;
        currentTable = 0;
        moveStack(stack,currentPosition,currentTable,outFile2);
        printTable(currentTable,outFile2);
        currentPosition++;
        currentTable = 1;
        previousTable = 0;
        int currentQueue = 0;
        while(currentPosition < numDigits){
            while(currentQueue < tableSize){
                //loop until hashTable[previousTable][currentQueue] is empty.
                while(!(hashTable[previousTable][currentQueue]->isEmpty())){//old:hashTable[previousTable][currentQueue]->head->next != NULL && hashTable[previousTable][currentQueue]->tail!=NULL
                    listNode* node = hashTable[previousTable][currentQueue]->deleteQ();//deleteQ (hashTable[previousTable][currentQueue])
                    int hashIndex = getDigit(node->val,currentPosition);//getDigit (newNode's data, currentPosition)
                    hashTable[currentTable][hashIndex]->insertQ(node);// add newNode at the tail of the queue -- hashTable[currentTable][hashIndex]
                   
                }
                currentQueue++;
            }
            outFile2<<"***Printing Table" <<endl;
            printTable(currentTable,outFile2);
            previousTable = currentTable;
            currentTable = (currentTable + 1)%2;
            currentQueue = 0;
            currentPosition++;

        }//(end of loop) repeat while currentPosition < numDigits
        printSortedData(previousTable,outFile1);
    }
    // Moves all nodes on the stack to the first hash table.
    void moveStack(LLstack* stk,int  currentPosition,int currentTable, ofstream &outFile2){
        outFile2 << "*** Performing moveStack" << endl;
        
        // move nodes from stack to hashTable[0]
        while(!stk->isEmpty()){
            
            listNode* node = stk->pop();
            int hashIndex = getDigit(node->val,currentPosition);
            hashTable[currentTable][hashIndex]->insertQ(node);
            
        }

    }
    // Determines and returns the length of a given data.
    //suggestion: convert data to string to get the length.
    int getLength (int data){
        string temp = to_string(data);
        if(data>0){
            return temp.length();
        }else if(data < 0){
            return temp.length()-1;
        }

        return 0;   
    }
    //Determines and returns the digit at the position of data. On your own!.
    int getDigit (int data,int currentPosition){
       
       if(currentPosition==0){
           return data%10; //if CurrentPosition is 0 then return the last digit
       }

       data/=10;//get rid of the last digit on the data
       currentPosition-=1;
       return getDigit(data,currentPosition);// recursing till currentPosition is zero becuase digit length - currentPosition gives us the value we are looking for
    }


    // Call printQueue () for each none empty queue in hashTable[whichTable].
    void printTable (int whichTable,ofstream &outFile2){
        for(int i =0;i<10;i++){
            if(hashTable[whichTable][i]->head->next != NULL){
                hashTable[whichTable][i]->printQ(whichTable,i,hashTable[whichTable][i],outFile2);
            }
        }
    }
    // Print each none empty queue in hashTable[whichTable], one data per text line;
    //*** make sure to subtract offSet before printing the data.
    //check email
    void printSortedData (int whichTable,ofstream &outFile1){
         for(int i =0;i<10;i++){
            if(hashTable[whichTable][i]->head->next != NULL){
                listNode* curr = hashTable[whichTable][i]->head->next;
                listNode* temp = curr;
                while(curr != NULL){
                    outFile1<< (curr->val)- offset <<endl;
                    curr = curr->next;
                }
                curr = temp;
            }
        }
    }


    

};



int main(int argc,char* argv[]){
    ifstream input;
    ofstream output1, output2;
    input.open(argv[1]);
    output1.open(argv[2]);
    output2.open(argv[3]);
    if(input.fail()){
        cout<< "error opening input file, make sure the name of file is typed correctly"<<endl;
    }
  
    
    RadixSort* srt = new RadixSort();
   
    srt->firstReading(input,output2);
    input.close();

    input.open(argv[1]);
    LLstack* stack = srt->loadStack(input,output2);
    stack->printStack(output2,stack);
    srt->RSort(stack,output1,output2);
    

    input.close();
    output1.close();
    output2.close();
    
   
    

    
}