#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int pathLength;
    cin >> pathLength;
    int dp[pathLength+1][4] = {};
    dp[0][0] = 1;
    for (int i = 0; i < pathLength; ++i) {
        dp[i+1][0] = (int) (((long long)dp[i+1][0] + dp[i][1] + dp[i][2] + dp[i][3]) % 1000000007);
        dp[i+1][1] = (int) (((long long)dp[i+1][1] + dp[i][0] + dp[i][2] + dp[i][3]) % 1000000007);
        dp[i+1][2] = (int) (((long long)dp[i+1][2] + dp[i][0] + dp[i][1] + dp[i][3]) % 1000000007);
        dp[i+1][3] = (int) (((long long)dp[i+1][3] + dp[i][0] + dp[i][1] + dp[i][2]) % 1000000007);
    }
    cout << dp[pathLength][0];
    return 0;
}