// Author : warks

use std::io::prelude::*;
use std::io::{BufReader, stdin, BufWriter, stdout, Write};
use std::collections::HashMap;

fn main() {
    let mut reader = BufReader::new(stdin());
    let mut out = BufWriter::new(stdout());

    let mut input = String::new();
    reader.read_line(&mut input)
        .expect("Line not found.");
    
    let input: Vec<usize> = input.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();
    let (_noodle_count, _meal_count) = (input[0], input[1]);

    let mut input = String::new();
    reader.read_line(&mut input)
        .expect("Line not found.");
    let noodles: Vec<u32> = input.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();
    
    input = String::new();
    reader.read_line(&mut input)
        .expect("Line not found.");
    let meals: Vec<u32> = input.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();
    
    let possible = is_plan_possible(noodles, meals);
    writeln!(out, "{}", if possible { "Yes" } else { "No" }).ok();
}

fn is_plan_possible(noodles: Vec<u32>, meals: Vec<u32>) -> bool {
    let mut noodle_freq: HashMap<u32, usize> = HashMap::new();
    for noodle in noodles.into_iter() {
        if let Some(prev) = noodle_freq.get_mut(&noodle) {
            *prev += 1;
        } else {
            noodle_freq.insert(noodle, 1);
        }
    }

    for meal in meals.into_iter() {
        if let Some(prev) = noodle_freq.get_mut(&meal) {
            if *prev == 0 {
                return false;
            } else {
                *prev -= 1;
            }
        } else {
            return false;
        }
    }

    true
}