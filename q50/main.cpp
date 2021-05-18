
//Mohammed Chowdhury
//CS 323-33

#include <iostream>
#include<fstream>


using namespace std;

class listNode{
    public:
    int data;
    listNode* next;
    //empty constructor
    listNode(){}
    //if a node object is created, set the nodes value to data and have its next node point to null
    listNode(int val){
        data = val;
        next = NULL;
    }

    //prints node
    void printNode(ofstream &output){
        //(node's data, data of node's next) ->
        if(next == NULL){
            output << "(" << data << ", NULL)-->";
        }else{
            output << "(" << data << ","<< next->data << ")-->";
        }
        
    }
};

class LList{
    public:
    listNode* listHead;
    int size = 0;

    LList(){
        listHead = new listNode(-999);
    }

    LList(listNode* head){
        listHead = head;
    }

    void constructLL(ifstream &inFile){
        int n;
        while (!inFile.eof()){
        inFile >> n;
        listInsert(new listNode(n));
        }
    }

    void reverseLL(listNode* head,ofstream &outFile2){
        listNode* last = head->next;
        while(last->next != NULL){
            listNode* spot = last->next;
            last->next = spot->next;
            spot->next = head->next;
            head->next = spot;
            printList(outFile2);
        }

    }

    void listInsert(listNode* node){
        listNode* curr = listHead;
        listNode* temp = listHead;
        bool inserted = false;
        if(isEmpty()){
            curr->next = node;
            size++;
        }else{
            while(curr->next != NULL){
                if(node->data < curr->next->data){
                    listNode* tmp2 = curr->next;
                    curr->next = node;
                    node->next = tmp2;
                    size++;
                    inserted = true;
                    break;
                }
                curr = curr->next;
            }
            if(!inserted){
                curr->next = node;
                inserted = true;
            }
        }
        curr = temp;

    }
    void printList( ofstream &outFile1){
        listNode* temp = listHead;
        while(temp->next != NULL){
            temp->printNode(outFile1);
            temp= temp ->next;
        }
        outFile1<< "NULL" <<endl;
    }

    bool isEmpty(){
        return size == 0;
    }
};

int main(int argc, char* argv[]){
    ifstream inFile;
    ofstream outFile1,outFile2;
    inFile.open(argv[1]);
    if(inFile.fail()){
        cout << "File failed to open, make sure input file name is typed correctly"<<endl;
        exit(1);
    }
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);
    listNode* head = new listNode(-999);
    LList* list = new LList(head);
    list->constructLL(inFile);
    list->printList(outFile1);
    list->reverseLL(list->listHead,outFile2);
}