// Author : warks

use std::io;

fn main() {
    let mut input = String::new();
    io::stdin()
    .read_line(&mut input)
    .expect("Failed to read.");

    let input: Vec<u8> = input.split_whitespace()
        .map(|x| x.parse().expect("Not an integer."))
        .collect();

    let (a, b) = (input[0], input[1]);

    let possible = (a == 1 && b == 10) || (b == a + 1);

    println!("{}", if possible { "Yes" } else { "No" });
}