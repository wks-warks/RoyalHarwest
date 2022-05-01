#include <bits/stdc++.h>
using namespace std;
int main()
{
    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        vector<int> vec;
        vector<int> vec1;
        int a;
        cin >> a;
        for (int j = 0; j < a; j++)
        {
            int v;
            cin >> v;
            vec.push_back(v);
        }
        sort(vec.begin(), vec.end());
        // vector<int> vec1;
        int x = 0;
        for (int j = 0; j < vec.size() - 1; j++)
        {
            x = vec[j + 1] - vec[j];

            vec1.push_back(x);
        }
        sort(vec1.begin(), vec1.end());
        for (int j = 0; j < 1; j++)
        {
            cout << vec1[j] << '\n';
        }
    }
}