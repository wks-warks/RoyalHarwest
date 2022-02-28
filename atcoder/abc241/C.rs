// Author : warks

use std::io::prelude::*;
use std::io::{BufReader, stdin, BufWriter, stdout, Write};

fn main() {
    let mut reader = BufReader::new(stdin());
    let mut out = BufWriter::new(stdout());

    let mut input = String::new();
    reader.read_line(&mut input)
        .expect("Line not found.");
    
    let n = input.trim()
        .parse::<usize>()
        .expect("Not an integer.");
    
    let mut grid: Vec<String> = Vec::new();
    for _ in 0..n {
        let mut input = String::new();
        reader.read_line(&mut input)
        .expect("Line not found.");

        grid.push(String::from(input.trim()));
    }

    let possible = is_possible(grid, 6, 2);
    writeln!(out, "{}", if possible { "Yes" } else { "No" }).ok();
}

fn is_possible(grid: Vec<String>, length: usize, changeable: usize) -> bool {
    let mut char_grid: Vec<Vec<char>> = Vec::new();
    for row in grid.iter() {
        let chars: Vec<char> = row.chars().collect();
        char_grid.push(chars);
    }

    for i in 0..char_grid.len() {
        for j in 0..char_grid.len() {
            if is_possible_check(&char_grid, length, changeable, i, j) {
                return true;
            }
        }
    }

    false
}

fn is_possible_check(char_grid: &Vec<Vec<char>>, length: usize, changeable: usize, i: usize, j: usize) -> bool {
    let possible = is_possible_local_check(char_grid, length, changeable, i, j, 0, 1)
        || is_possible_local_check(char_grid, length, changeable, i, j, 1, 0)
        || is_possible_local_check(char_grid, length, changeable, i, j, 1, 1)
        || is_possible_local_check(char_grid, length, changeable, i, j, 1, -1);

    possible
}

fn is_possible_local_check(char_grid: &Vec<Vec<char>>, length: usize, changeable: usize, i: usize, j: usize, i_inc: usize, j_inc: isize) -> bool {
    let i_end = i + i_inc * (length - 1);
    if i_end >= char_grid.len() {
        return false;
    }

    let j_end = j as isize + (j_inc * (length - 1) as isize);
    if j_end < 0 || j_end >= char_grid.len() as isize {
        return false;
    }

    let mut count = 0;
    for inc in 0..length {
        let effective_i = i + i_inc * inc;
        let effective_j = (j as isize + (j_inc * inc as isize)) as usize;

        if char_grid[effective_i][effective_j] == '#' {
            count += 1;
        }

    }

    return count >= length - changeable;
}