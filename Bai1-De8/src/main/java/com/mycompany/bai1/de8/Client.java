/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bai1.de8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author thanh
 */
public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Tạo ma trận để gửi
            int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };

            // Gửi kích thước ma trận
            int rows = matrix.length;
            int cols = matrix[0].length;
            out.println(rows + " " + cols);

            // Gửi từng dòng của ma trận
            for (int[] row : matrix) {
                StringBuilder rowStr = new StringBuilder();
                for (int value : row) {
                    rowStr.append(value).append(" ");
                }
                out.println(rowStr.toString().trim());
            }

            // Nhận kết quả từ server
            String result = in.readLine();
            System.out.println("Phan tu lon nhat theo tung dong: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}