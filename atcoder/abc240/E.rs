// Author : warks

use std::io::prelude::*;
use std::io::{BufReader, stdin, BufWriter, stdout, Write};
use std::cmp::max;

fn main() {
    let mut reader = BufReader::new(stdin());
    let mut out = BufWriter::new(stdout());

    let mut line = String::new();
    reader.read_line(&mut line)
        .expect("Line not found");
    let node_count = line.trim()
        .parse::<usize>()
        .expect("Not an integer.");
    
    let mut adj: Vec<Vec<usize>> = Vec::with_capacity(node_count);
    for _ in 0..node_count {
        adj.push(Vec::new());
    }

    for _ in 1..node_count {
        line = String::new();
        reader.read_line(&mut line)
            .expect("Line not found.");
        let input: Vec<usize> = line.split_whitespace()
            .map(|x| x.parse().expect("Not an integer."))
            .collect();
        let (u, v) = (input[0] - 1, input[1] - 1);

        adj[u].push(v);
        adj[v].push(u);
    }

    let ranges = get_ranges(adj, 0, node_count);
    for range in ranges.into_iter() {
        writeln!(out, "{} {}", range.0, range.1).ok();
    }
}

fn get_ranges(adj: Vec<Vec<usize>>, root: usize, node_count: usize) -> Vec<(usize, usize)> {
    let mut ranges = vec![(0, 0); node_count];

    fill_ranges(&adj, &mut ranges, root, 0, 1);

    ranges
}

fn fill_ranges(adj: &Vec<Vec<usize>>, ranges: &mut Vec<(usize, usize)>, root: usize, parent: usize, start: usize) -> usize {
    ranges[root].0 = start;
    let mut increment = 0;

    for nbr in adj[root].iter() {
        if *nbr != parent {
            increment += fill_ranges(adj, ranges, *nbr, root, start + increment);
        }
    }

    increment = max(increment, 1);
    ranges[root].1 = start + increment - 1;
    increment
}