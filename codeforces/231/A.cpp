#include <bits/stdc++.h>

using namespace std;

bool is_solvable(int p, int v, int t);

int main() {
    int n;
    cin >> n;

    int solvable = 0;
    for (int i = 0; i < n; i++) {
        int p, v, t;
        cin >> p >> v >> t;

        if (is_solvable(p, v, t)) {
            solvable++;
        }
    }
    cout << solvable << endl;
}

bool is_solvable(int p, int v, int t) {
    return (p + v + t >= 2);
}