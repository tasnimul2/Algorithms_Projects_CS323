#include <iostream>
#include <fstream>
using namespace std;


int main(int argc, char* argv[]){
    cout << "hello" << endl;
    ifstream infile; 
    infile.open(argv[1]);
    if(infile.fail()){
        cout<< "error opening input file, make sure the name of file is typed correctly"<<endl;
    }
    int num;
    while(!infile.eof()){
        infile >> num;
        //cout << num << " " ;
    }
}