package com.github.varenytsiamykhailo.BigDataLabs.lab1;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    static final String nonSymbolsRegExp = "[^a-zа-я]";
    static final String spacesRegExp = " +"; // " +"  - один и более пробелов

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //        получаем строку из value, удаляем все спецсимволы, переводим в
        //        lowercase, разбиваем на слова и каждое слово пишем в контекст с счетчиком 1
        //        в контекст пишется пара — Text и IntWritable

        String line = value.toString();
        String[] words = parseLine(line);
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }

    }

    private static String[] parseLine(String line) {
        line = line.toLowerCase().trim().replaceAll(nonSymbolsRegExp, " ");
        return line.split(spacesRegExp);
    }

}
