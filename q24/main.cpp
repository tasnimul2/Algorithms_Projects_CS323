#include <iostream>
#include<fstream>
using namespace std;

int max(ifstream &inFile){
    int num = -99999;
    int biggest = -99999;
    char dummy;
    //inFile >> dummy >> dummy >> dummy; //comment out on windows 
    while(inFile >>  num){
        //inFile >>  num;
        if(num >= biggest ){
            biggest = num;
        }
    }
    return biggest;
}
int inc(ifstream &inFile, int arr[]){
    int num;
    
    char dummy;
    //inFile >> dummy >> dummy >> dummy; //comment out on windows 
    while(inFile >>  num){
        //inFile >>  num;
        arr[num]++;   
    }
}

int main(int argc, char* argv[]){
    ifstream input;
    ofstream output;

    input.open(argv[1]);
    if(input.fail()){
        cout<< "File failed to open, make sure input file name is typed correctly"<<endl;
        exit(1);
    }
    output.open(argv[2]);
    int size = max(input);
    int dataAry[size];
    input.close();
    input.open(argv[1]);
    for(int i = 0 ; i < size; i ++){
         dataAry[i] = 0;
    }
    inc(input,dataAry);
    for(int i = 0 ; i < size; i ++){
        for(int x = 0; x < dataAry[i]; x++){
            output<< i << endl;
        }
        
    }
}