// Author : warks

use std::io;

fn main() {
    let mut input = String::new();
    io::stdin()
    .read_line(&mut input)
    .expect("Line not found.");
    let input: Vec<u16> = input.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();
    let (jumps, final_pos) = (input[0], input[1]);
    
    let mut reachable = vec![vec![false; 1 + final_pos as usize]; 2];
    let mut curr = 0;
    reachable[curr][0] = true;

    for _ in 0..jumps {
        let prev = curr;
        curr ^= 1;

        let mut input = String::new();
        io::stdin()
        .read_line(&mut input)
        .expect("Line not found.");
        let input: Vec<u8> = input.split_whitespace()
            .map(|x| x.parse().expect("Not an integer."))
            .collect();
        let (a, b) = (input[0], input[1]);
        
        for pos in (0..reachable[curr].len()).rev() {
            reachable[curr][pos] = false; // Overwriting earlier data.
            
            if pos >= a as usize {
                reachable[curr][pos] |= reachable[prev][pos - a as usize];
            }
            if pos >= b as usize {
                reachable[curr][pos] |= reachable[prev][pos - b as usize];
            }
        }
    }

    println!("{}", if reachable[curr][final_pos as usize] { "Yes" } else { "No" });
}