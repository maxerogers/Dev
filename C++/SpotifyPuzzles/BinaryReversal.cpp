//
//  main.cpp
//  BinaryReversal
//
//  Created by Max Rogers on 5/30/13.
//  Copyright (c) 2013 Max Rogers. All rights reserved.
//
// So this programs job is to take in input from the user,
// Convert that integer into a binary representation
// And then reverse it
//
// This verion's method will be to:
// First, convert int into a vector<int> holding a binary digit in each index
// Second, to reverse that order to produce a string that is reversed binary number
//
// I used a vector<int> data structure because my goal is to avoid storing unnecessary zeros
// so a dynamic datastructure was best
//
// NOTE!!!!!!:
// I wrote this code in XCode so please use a clang++ complier. I know g++ doesn't like my to_string methods
// clang++ -std=c++11 -stdlib=libc++ -Weverything BinaryReversal.cpp -o BR
// ./BR


//preprocessor
#include <iostream>
#include <string>
#include <vector>

#define MAX 1073741824 // Input range is 1 to a billion. This is the next largest binary number
#define POWER 30 // the power of MAX --- 2**POWER = MAX

using namespace std;
//protypes
vector<int> makeBinaryVector(int x);
int reverseBinaryNumber(vector<int> v);
string toBinaryString(vector<int> v);
string toReverseBinaryString(vector<int> v);
string printMemo();

int *memo; //This stores the powers of 2 dynamically for later use

//methods
int main(int argc, const char * argv[])
{
    //Taking in console input
    string temp;
    getline(cin,temp);
    int input = stoi(temp);
    
    //seting up memo for dynamic calling of powers of 2
    memo = new int[POWER + 1];
    
    cout << "The input is " << input << endl;
    vector<int> vec = makeBinaryVector(input);
    string binary = toBinaryString(vec);
    string reverse = toReverseBinaryString(vec);
    cout << input << " is " << binary << " in binary " << endl;
    cout << reverse << " is that reversed"<< endl;
    cout << reverse << " is equal to " << reverseBinaryNumber(vec) << endl;
   // cout << printMemo() << endl;
    return 0;
}

//
// This is the meat and potatoes
// This method's goal is to
// A: Produce a vector that represents the input X in a binary format where each element of the vector is a binary digit
// B: initilized memo for later use
//
// To accomplish this we must iterate from the largest number (2**POWER) to 2**1. With every iteration we store the
// current cursor number (2**i) in the memo pad for later and compare it to our input.
// if it is larger than our input then we do nothing or place a zero after the first reccurences of a 1
// if it is smaller than our input then we subtract it from input and store a 1 in the vector at index i
// we continue doing this until we reach the end and check if input == cursor if so store 1 if not store 0 and 2**0 is 1

// in a more algorithmic form
// N is maximum possible input from the user
// MAX is the next largest power of 2 after N
// Memo = set of powers of 2 from 0 to log2 (MAX)
// mBV(V,y,x):- V sequence of binary digits, y is the current cursor and x is the remainder of the users input
// mBV(V,1,x):-
//      if(x == y) V.append(1)
//      else    v.append(0)
// mBV(V,y,x):-
//      if(y>x) V.append(0) but only if there is already a 1 in V
//      else V.append(1)
//            x -= y
//      mBV(V,y/2,x)
//
// The runs in O(n) where n is POWER
// if I could identify the spot for the first 1, this part of the program could be done in a faster linear time. 
// I'm afraid that finding that spot will cost me all the time I would save so I decided to stick with this solid
// and dependenable method.
        
            
vector<int> makeBinaryVector(int x)
{
    vector<int> result;
    bool flag = false; // the flag used to triger '0' storage
    int y = MAX;
    for(int i=POWER;i>0;i--,y/=2)
    {
        memo[i] = y;
        if(y > x)
        {
            if(flag)
                result.push_back(0);
        }else{
            flag = true;
            result.push_back(1);
            x -= y;
        }
    }
    if(y == x)
        result.push_back(1);
    else
        result.push_back(0);
    memo[0] = 1;
    return result;
}


//The convetor method

int reverseBinaryNumber(vector<int> v)
{
    int result = 0;
    vector<int> vec2;
    for(int i= (int)v.size()-1;i>=0;i--)
    {
        if(v[i] == 1)
            result += memo[i];
    }
    return result;
}

//To String Method

string printMemo()
{
    string result = "";
    for(int i=0;i<POWER;i++)
    {
        result += to_string(memo[i]);
        result += ", ";
    }
    return result;
}

string toBinaryString(vector<int> v)
{
    string result = "";
    for(int i=0;i<v.size();i++)
    {
        result += to_string(v[i]);
    }
    return result;
}

string toReverseBinaryString(vector<int> v)
{
    string result = "";
    for(int i=(int)v.size()-1;i>=0;i--)
    {
        result += to_string(v[i]);
    }
    return result;
}

