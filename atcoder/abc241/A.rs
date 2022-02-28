// Author : warks

use std::io::prelude::*;
use std::io::{BufReader, stdin, BufWriter, stdout, Write};

fn main() {
    let mut reader = BufReader::new(stdin());
    let mut out = BufWriter::new(stdout());

    let mut input = String::new();
    reader.read_line(&mut input)
        .expect("Line not found.");

    let a: Vec<usize> = input.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();
    
    let num = get_num(a, 0, 3);
    writeln!(out, "{}", num).ok();
}

fn get_num(a: Vec<usize>, pos: usize, moves: usize) -> usize {
    if moves == 0 {
        return pos;
    } else {
        let new_pos = a[pos];
        return get_num(a, new_pos, moves-1);
    }
}