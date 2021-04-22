#include <iostream>
#include <vector>

using namespace std;

vector<int> get_removal_indices(int arr_size, int*& arr);

int main() {
  int arr_size;
  cin >> arr_size;

  int* arr = new int[arr_size];
  for (int i = 0; i < arr_size; i++) {
    cin >> arr[i];
  }

  vector<int> removal_indices = get_removal_indices(arr_size, arr);
  cout << removal_indices.size() << "\n";
  for (int i = 0; i < int(removal_indices.size()); i++) {
    cout << removal_indices[i] << " ";
  }
}

vector<int> get_removal_indices(int arr_size, int*& arr) {
  vector<vector<int>> indices_to_reach;
  indices_to_reach.reserve(200'000);

  for (int s = 0; s <= 200'000; s++) {
    vector<int> v;
    indices_to_reach.emplace_back(v);
  }

  int sum = 0;
  for (int i = 0; i < arr_size; i++) {
    int num = arr[i];
    for (int j = sum; j >= 0; j--) {
      if (j != 0 && indices_to_reach[j].size() == 0) {
        continue;
      }

      
      if (indices_to_reach[j+num].size() == 0 || indices_to_reach[j+num].size() > indices_to_reach[j].size()) {
        indices_to_reach[j+num].clear();
        for (auto iter = indices_to_reach[j].begin(); iter != indices_to_reach[j].end(); iter++) {
          indices_to_reach[j+num].emplace_back(*iter);
        }
        indices_to_reach[j+num].emplace_back(i+1);
      }
    }

    sum += num;
  }

  int min_removed = arr_size;
  vector<int> removal_indices;
  for (int s = 0; s < sum; s++) {
    if(indices_to_reach[s].size() == 0 && s != 0) {
      continue;
    }

    int removed = indices_to_reach[s].size();
    int removed_sum = sum - s;

    if ((removed_sum & 1) || indices_to_reach[removed_sum>>1].size() == 0) {
      if (removed < min_removed) {
        min_removed = removed;
        removal_indices = indices_to_reach[s];
      }
    }
  }
  
  return removal_indices;
}