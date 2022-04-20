// Author : warks

#![allow(dead_code)]
#![allow(unused_variables)]
#![allow(unused_mut)]
#![allow(unused_imports)]

use std::io::Write;
use std::collections::BTreeSet;
use std::collections::HashMap;

fn main() {
    let stdin = std::io::stdin();
    let mut buf_reader = std::io::BufReader::new(stdin.lock());
    let mut scan = ContestScanner {
        buffer: Vec::new(),
        reader: buf_reader,
        ptr: 0
    };
    let stdout = std::io::stdout();
    let mut out = &mut std::io::BufWriter::new(stdout.lock());

    let tests = scan.next_usize();
    for _ in 0..tests {
        let n = scan.next_usize();
        let mut a = Vec::with_capacity(n);
        for _ in 0..n {
            a.push(scan.next_i32());
        }

        let max_value = get_max_value(a);
        writeln!(out, "{}", max_value).ok();
    }
}

fn get_max_value(a: Vec<i32>) -> i32 {
    let mut prefix_scores = vec![-1_000_000_000; a.len() + 1];    
    let ps = get_prefix_sum(&a);
    let compressed_prefix_coordinates = get_compressed_coordinates(&ps);
    let mut pst = SegmentTree::new(compressed_prefix_coordinates);

    prefix_scores[0] = 0;
    pst.update(0, 0, 0, 0);
    for i in 1..ps.len() {
        let sum = ps[i];
        let (optimal_positive, optimal_neutral, optimal_negative) = pst.get_optimal_scores(sum);
        let optimal_score = (optimal_positive + i as i32).max(optimal_neutral.max(optimal_negative - i as i32));
    
        prefix_scores[i] = optimal_score;
        pst.update(sum, optimal_score - i as i32, optimal_score, optimal_score + i as i32);
    }

    let mut suffix_scores = vec![-1_000_000_000; a.len() + 1];
    let ss = get_suffix_sum(&a);
    let compressed_suffix_coordinates = get_compressed_coordinates(&ss);
    let mut sst = SegmentTree::new(compressed_suffix_coordinates);

    suffix_scores[a.len()] = 0;
    sst.update(0, 0, 0, 0);
    for i in 1..ss.len() {
        let j = a.len() - i;
        let sum = ss[j];
        let (optimal_positive, optimal_neutral, optimal_negative) = sst.get_optimal_scores(sum);
        let optimal_score = (optimal_positive + i as i32).max(optimal_neutral.max(optimal_negative - i as i32));

        suffix_scores[j] = optimal_score;
        sst.update(sum, optimal_score - i as i32, optimal_score, optimal_score + i as i32);
    }

    let mut score = -1_000_000_000;
    for i in 0..=a.len() {
        // println!("debug: {} {} {}", i, prefix_scores[i], suffix_scores[i]);
        score = score.max(prefix_scores[i] + suffix_scores[i]);
    }
    score
}

fn get_suffix_sum(a: &Vec<i32>) -> Vec<i64> {
    let mut ss = vec![0; a.len() + 1];
    for i in (0..a.len()).rev() {
        ss[i] = ss[i+1] + a[i] as i64;
    }
    ss
}

fn get_prefix_sum(a: &Vec<i32>) -> Vec<i64> {
    let mut ps = vec![0; a.len() + 1];
    for i in 0..a.len() {
        ps[i+1] = ps[i] + a[i] as i64;
    }
    ps
}

fn get_compressed_coordinates(a: &Vec<i64>) -> HashMap<i64, usize> {
    let mut set = BTreeSet::new();
    for num in a.iter() {
        set.insert(*num);
    }

    let mut compressed_coordinates = HashMap::with_capacity(set.len());
    for (i, num) in set.iter().enumerate() {
        compressed_coordinates.insert(*num, i);
    }
    compressed_coordinates
}

struct SegmentTree {
    compressed_coordinates: HashMap<i64, usize>,
    st: Vec<SegmentTreeNode>,
    n: usize
}

#[derive(Debug)]
#[derive(Clone)]
struct SegmentTreeNode {
    positive_score: i32,
    neutral_score: i32,
    negative_score: i32
}

impl SegmentTree {
    fn new(compressed_coordinates: HashMap<i64, usize>) -> SegmentTree {
        let n = compressed_coordinates.len();
        let st = vec![SegmentTreeNode {positive_score: -1_000_000_000, neutral_score: -1_000_000_000, negative_score: -1_000_000_000}; n<<1];
        SegmentTree {compressed_coordinates, st, n}
    }

    fn get_optimal_scores(&self, sum: i64) -> (i32, i32, i32) {
        let mut optimal_score = (-1_000_000_000, -1_000_000_000, -1_000_000_000);
        let idx = *self.compressed_coordinates.get(&sum).unwrap();
        
        let optimal_score = (self.query(0, idx).0, self.query(idx, idx+1).1, self.query(idx+1, self.n).2);

        optimal_score
    }

    fn query(&self, mut left: usize, mut right: usize) -> (i32, i32, i32) {
        let mut result = (-1_000_000_000, -1_000_000_000, -1_000_000_000);

        left += self.n;
        right += self.n;
        while left < right {
            if (left & 1) == 1 {
                result.0 = result.0.max(self.st[left].positive_score);
                result.1 = result.1.max(self.st[left].neutral_score);
                result.2 = result.2.max(self.st[left].negative_score);
                left += 1;
            }

            if (right & 1) == 1 {
                right -= 1;
                result.0 = result.0.max(self.st[right].positive_score);
                result.1 = result.1.max(self.st[right].neutral_score);
                result.2 = result.2.max(self.st[right].negative_score);
            }

            left >>= 1;
            right >>= 1;
        }

        result
    }

    fn update(&mut self, sum: i64, positive_score: i32, neutral_score: i32, negative_score: i32) -> () {
        let mut idx = *self.compressed_coordinates.get(&sum).unwrap();
        idx += self.n;
        self.st[idx].positive_score = self.st[idx].positive_score.max(positive_score);
        self.st[idx].neutral_score = self.st[idx].neutral_score.max(neutral_score);
        self.st[idx].negative_score = self.st[idx].negative_score.max(negative_score);

        while idx > 1 {
            let par = idx>>1;
            let sib = idx^1;

            self.st[par].positive_score = self.st[idx].positive_score.max(self.st[sib].positive_score);
            self.st[par].neutral_score = self.st[idx].neutral_score.max(self.st[sib].neutral_score);
            self.st[par].negative_score = self.st[idx].negative_score.max(self.st[sib].negative_score);

            idx = par;
        }
    }
}

// ---------- Begin: ContestScanner ----------
// Assumes guarantee over inputs.
struct ContestScanner<R: std::io::BufRead> {
    buffer: Vec<u8>,
    reader: R,
    ptr: usize
}

impl <R> ContestScanner<R> where R: std::io::BufRead {
    fn next_i128(&mut self) -> i128 {
        self.settle_buffer_and_ptr();
        let mut n: i128 = 0;
        let mut minus = false;
        
        let mut b = self.read_byte();
        if b == '-' as u8 {
            minus = true;
            b = self.read_byte();
        }

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as i128;
            }

            if self.ptr == self.buffer.len() {
                return if minus { -n } else { n };
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return if minus { -n } else { n };
            }
        }
    }

    fn next_i64(&mut self) -> i64 {
        self.settle_buffer_and_ptr();
        let mut n: i64 = 0;
        let mut minus = false;
        
        let mut b = self.read_byte();
        if b == '-' as u8 {
            minus = true;
            b = self.read_byte();
        }

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as i64;
            }

            if self.ptr == self.buffer.len() {
                return if minus { -n } else { n };
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return if minus { -n } else { n };
            }
        }
    }

    fn next_i32(&mut self) -> i32 {
        self.settle_buffer_and_ptr();
        let mut n: i32 = 0;
        let mut minus = false;
        
        let mut b = self.read_byte();
        if b == '-' as u8 {
            minus = true;
            b = self.read_byte();
        }

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as i32;
            }

            if self.ptr == self.buffer.len() {
                return if minus { -n } else { n };
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return if minus { -n } else { n };
            }
        }
    }

    fn next_i16(&mut self) -> i16 {
        self.settle_buffer_and_ptr();
        let mut n: i16 = 0;
        let mut minus = false;
        
        let mut b = self.read_byte();
        if b == '-' as u8 {
            minus = true;
            b = self.read_byte();
        }

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as i16;
            }

            if self.ptr == self.buffer.len() {
                return if minus { -n } else { n };
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return if minus { -n } else { n };
            }
        }
    }

    fn next_i8(&mut self) -> i8 {
        self.settle_buffer_and_ptr();
        let mut n: i8 = 0;
        let mut minus = false;
        
        let mut b = self.read_byte();
        if b == '-' as u8 {
            minus = true;
            b = self.read_byte();
        }

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as i8;
            }

            if self.ptr == self.buffer.len() {
                return if minus { -n } else { n };
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return if minus { -n } else { n };
            }
        }
    }

    fn next_isize(&mut self) -> isize {
        self.settle_buffer_and_ptr();
        let mut n: isize = 0;
        let mut minus = false;
        
        let mut b = self.read_byte();
        if b == '-' as u8 {
            minus = true;
            b = self.read_byte();
        }

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as isize;
            }

            if self.ptr == self.buffer.len() {
                return if minus { -n } else { n };
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return if minus { -n } else { n };
            }
        }
    }

    fn next_u128(&mut self) -> u128 {
        self.settle_buffer_and_ptr();
        let mut n: u128 = 0;
        
        let mut b = self.read_byte();

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as u128;
            }

            if self.ptr == self.buffer.len() {
                return n;
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return n;
            }
        }
    }

    fn next_u64(&mut self) -> u64 {
        self.settle_buffer_and_ptr();
        let mut n: u64 = 0;
        
        let mut b = self.read_byte();

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as u64;
            }

            if self.ptr == self.buffer.len() {
                return n;
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return n;
            }
        }
    }

    fn next_u32(&mut self) -> u32 {
        self.settle_buffer_and_ptr();
        let mut n: u32 = 0;
        
        let mut b = self.read_byte();

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as u32;
            }

            if self.ptr == self.buffer.len() {
                return n;
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return n;
            }
        }
    }

    fn next_u16(&mut self) -> u16 {
        self.settle_buffer_and_ptr();
        let mut n: u16 = 0;
        
        let mut b = self.read_byte();

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as u16;
            }

            if self.ptr == self.buffer.len() {
                return n;
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return n;
            }
        }
    }

    fn next_u8(&mut self) -> u8 {
        self.settle_buffer_and_ptr();
        let mut n: u8 = 0;
        
        let mut b = self.read_byte();

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit;
            }

            if self.ptr == self.buffer.len() {
                return n;
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return n;
            }
        }
    }

    fn next_usize(&mut self) -> usize {
        self.settle_buffer_and_ptr();
        let mut n: usize = 0;
        
        let mut b = self.read_byte();

        loop {
            if '0' as u8 <= b && b <= '9' as u8 {
                let digit = b - '0' as u8;
                n = n * 10 + digit as usize;
            }

            if self.ptr == self.buffer.len() {
                return n;
            }
            b = self.read_byte();
            if b < 33 || b > 57 {
                return n;
            }
        }
    }

    fn next_f64(&mut self) -> f64 {
        return self.next_string().parse::<f64>().expect("Not a floating point number.");
    }

    fn next_f32(&mut self) -> f32 {
        return self.next_string().parse::<f32>().expect("Not a floating point number.");
    }

    fn next_string(&mut self) -> String {
        self.settle_buffer_and_ptr();

        let mut end = self.buffer.len();
        for idx in self.ptr..self.buffer.len() {
            if self.buffer[idx] < 33 || self.buffer[idx] > 126 {
                end = idx;
                break;
            }
        }

        let start = self.ptr;
        self.ptr = if end == self.buffer.len() { end } else { end + 1 };
        return std::str::from_utf8(&self.buffer[start..end]).expect("Not a string.").to_string();
    }

    fn next_line(&mut self) -> String {
        let start = self.ptr;
        self.ptr = self.buffer.len();
        return std::str::from_utf8(&self.buffer[start..]).expect("Not a string.").to_string();
    }

    fn read_byte(&mut self) -> u8 {
        let byte = self.buffer[self.ptr];
        self.ptr += 1;
        byte
    }

    fn settle_buffer_and_ptr(&mut self) {
        if self.ptr == self.buffer.len() {
            self.ptr = 0;
            
            let mut input = String::new();
            self.reader.read_line(&mut input)
                .expect("Line not found.");
            self.buffer = input.into_bytes();
        }
        
        while self.ptr < self.buffer.len() && (self.buffer[self.ptr] < 33 || self.buffer[self.ptr] > 126) {
            self.ptr += 1;
        }

        if self.ptr == self.buffer.len() {
            self.settle_buffer_and_ptr();
        }
    }
}
// ---------- End: ContestScanner ----------