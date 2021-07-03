#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define MAX 111115

ll n,m,temp;
vector<ll>links[MAX];
ll cat_q[MAX];

ll rec(ll parent_node,ll node, ll cat_acc){
    if (cat_acc>m){
        return 0;
    } else if (links[node].size()==1){
        return 1;
    } else {
        ll counter = 0;
        for (ll sub_node:links[node]) {
            if(parent_node==sub_node){
                continue;
            } else {
                if (cat_q[node] == 0) cat_acc = 0;
                counter += rec(node,sub_node, cat_acc + cat_q[sub_node]);
            }
        }
        return counter;
    }
    exit(1);
}
int main ()
{
    cin>>n>>m;

    for (ll i = 1; i < n+1; ++i) {
        cin >>temp;
        cat_q[i]=temp;
    }

    ll a,b;
    while(cin>>a>>b){
        links[a].push_back(b);
        links[b].push_back(a);
    }
    links[1].push_back(0);
    cout<<rec(0,1,cat_q[1])<<endl;
    return 0;
}