#include<bits/stdc++.h>
using namespace std;
int main()
{
    int t;
    cin>>t;
    while(t--)
    {
        long long s=0;
        int n;
        cin>>n;
        long long t=n-(long int)n/2-1;
        for(int i=n;i>2;i-=2)
        {

            s+=(i*4-4)*t;
            t--;
        }
        cout<<s<<"\n";
    }
    return 0;
}