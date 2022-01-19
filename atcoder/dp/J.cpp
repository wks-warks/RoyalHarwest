#pragma GCC optimize("O3")
#include <iostream>
#include <iomanip>
#include <unordered_map>

#define get_cipher(ones, twos, threes) ((ones) + ((twos) << 10) + ((threes) << 20))
#define get_one_count(cipher) (((cipher) & ((1 << 10) - 1)))
#define get_two_count(cipher) ((((cipher) >> 10) & ((1 << 10) - 1)))
#define get_three_count(cipher) (((cipher) >> 20))

std::unordered_map<int, double> memo;
double compute_expected_operations(int dishes, int* &sushis);
double compute_expected_operations(int dishes, int cipher);


int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int dishes;
    std::cin >> dishes;

    int* sushis = new int[dishes];
    for (int i = 0; i < dishes; i++) {
        std::cin >> sushis[i];
    }

    double expected_operations = compute_expected_operations(dishes, sushis);
    std::cout << std::fixed << std::setprecision(10) << expected_operations;

    return 0;
}

double compute_expected_operations(int dishes, int* &sushis) {
    memo.insert({0, 0.0});
    int* counts = new int[4];
    for (int i = 0; i < 4; i++) {
        counts[i] = 0;
    }

    for (int d = 0; d < dishes; d++) {
        counts[sushis[d]]++;
    }

    int cipher = get_cipher(counts[1], counts[2], counts[3]);

    return compute_expected_operations(dishes, cipher);
}

double compute_expected_operations(int dishes, int cipher) {
    if (memo.find(cipher) != memo.end()) {
        return memo[cipher];
    }

    int ones = get_one_count(cipher);
    int twos = get_two_count(cipher);
    int threes = get_three_count(cipher);

    double ones_prob = ((double) ones) / dishes;
    double twos_prob = ((double) twos) / dishes;
    double threes_prob = ((double) threes) / dishes;
    double some_prob = ones_prob + twos_prob + threes_prob;

    double moves = 1.0 / some_prob;
    if (ones > 0) {
        ones--;
        moves += ones_prob / some_prob * compute_expected_operations(dishes, get_cipher(ones, twos, threes));
        ones++;
    }

    if (twos > 0) {
        twos--;
        ones++;
        moves += twos_prob / some_prob * compute_expected_operations(dishes, get_cipher(ones, twos, threes));
        twos++;
        ones--;
    }

    if (threes > 0) {
        threes--;
        twos++;
        moves += threes_prob / some_prob * compute_expected_operations(dishes, get_cipher(ones, twos, threes));
        threes++;
        twos--;
    }

    memo.insert({cipher, moves});
    return moves;
}