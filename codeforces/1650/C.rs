// Author : warks

#![allow(dead_code)]
#![allow(unused_variables)]
#![allow(unused_mut)]
#![allow(unused_imports)]

use std::io::{Write};

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

    let tests = scan.next_u16();
    for _ in 0..tests {
        let (segments, points) = (scan.next_u32(), scan.next_u32());
        let mut point_values = Vec::with_capacity(points as usize);
        for i in 0..points {
            let (pos, wt) = (scan.next_i32(), scan.next_i16());
            point_values.push((wt, (i + 1) as usize, pos));
        }

        let result = get_result(segments, point_values);
        writeln!(out, "{}", result.0).ok();
        for end_points in result.1.into_iter() {
            writeln!(out, "{} {}", end_points.0, end_points.1).ok();
        }
    }
}

fn get_result(segments: u32, point_values: Vec<(i16, usize, i32)>) -> (i32, Vec<(usize, usize)>) {
    let mut pq = std::collections::BinaryHeap::new();
    for values in point_values.into_iter() {
        pq.push(values);
    }

    while pq.len() > (segments << 1) as usize {
        pq.pop();
    }

    let mut net_wt = 0;
    let mut end_points = Vec::with_capacity((segments << 1) as usize);
    while !pq.is_empty() {
        let top = pq.pop().unwrap();
        net_wt += top.0 as i32;
        end_points.push((top.2, top.1));
    }

    end_points.sort();
    let (mut i, mut j) = (0, ((segments << 1) - 1) as usize);

    let mut end_point_pairs = Vec::with_capacity(segments as usize);
    while i < j {
        end_point_pairs.push((end_points[i].1, end_points[j].1));
        i += 1;
        j -= 1;
    }

    (net_wt, end_point_pairs)
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