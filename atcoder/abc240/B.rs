// Author : warks

use std::io;
use std::collections::HashSet;

fn main() {
    let mut input = String::new();
    io::stdin()
    .read_line(&mut input)
    .expect("Line not found.");
    let _n = input.trim().parse::<u16>();

    input = String::new();
    io::stdin()
    .read_line(&mut input)
    .expect("Line not found.");
    let values: Vec<u32> = input.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();
    
    let set:HashSet<u32> = values.into_iter().collect();
    println!("{}", set.len());
}