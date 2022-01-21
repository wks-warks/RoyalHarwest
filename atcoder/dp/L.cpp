#pragma GCC optimize("O3")
#include <iostream>
#include <unordered_map>


std::unordered_map<int, long long> maximum_memo;
long long get_maximum_diff(int* &a, int start, int end);

std::unordered_map<int, long long> minimum_memo;
long long get_minimum_diff(int* &a, int start, int end);


int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int n;
    std::cin >> n;
    int* a = new int[n];
    for (int i = 0; i < n; i++) {
        std::cin >> a[i];
    }

    long long diff = get_maximum_diff(a, 0, n-1);
    std::cout << diff << std::endl;

    return 0;
}

long long get_maximum_diff(int* &a, int start, int end) {
    if (start == end) {
        return a[start];
    }

    int key = (start << 15) + end;
    if (maximum_memo.find(key) != maximum_memo.end()) {
        return maximum_memo[key];
    }

    long long value = std::max(get_minimum_diff(a, start+1, end) + a[start], get_minimum_diff(a, start, end-1) + a[end]);
    maximum_memo.insert({key, value});
    return value;
}

long long get_minimum_diff(int* &a, int start, int end) {
    if (start == end) {
        return -a[start];
    }

    int key = (start << 15) + end;
    if (minimum_memo.find(key) != minimum_memo.end()) {
        return minimum_memo[key];
    }

    long long value = std::min(get_maximum_diff(a, start+1, end) - a[start], get_maximum_diff(a, start, end-1) - a[end]);
    minimum_memo.insert({key, value});
    return value;
}