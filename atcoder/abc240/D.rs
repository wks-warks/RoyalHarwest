// Author : warks

use std::io::prelude::*;
use std::io::{BufReader, stdin, BufWriter, stdout, Write};

fn main() {
    let mut reader = BufReader::new(stdin());
    let mut out = BufWriter::new(stdout());

    let mut line = String::new();
    reader.read_line(&mut line)
    .expect("Line not found.");
    let _balls = line.trim().parse::<u32>();

    line = String::new();
    reader.read_line(&mut line)
    .expect("Line not found.");
    let values: Vec<u32> = line.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();
    
    let states = get_states(&values);
    for state in states.into_iter() {
        writeln!(out, "{}", state).ok();
    }
}

fn get_states(values: &Vec<u32>) -> Vec<u32> {
    let mut queue: Vec<(u32, u32)> = Vec::new();
    let mut states: Vec<u32> = Vec::new();
    let mut total = 0;

    for value in values.into_iter() {
        total += 1;
        if let Some(end) = queue.last_mut() {
            if *value == end.0 {
                end.1 += 1;

                if end.0 == end.1 {
                    total -= end.1;
                    queue.pop();                    
                }
            } else {
                queue.push((*value, 1));
            }
        } else {
            queue.push((*value, 1));
        }

        states.push(total);
    }
    states
}