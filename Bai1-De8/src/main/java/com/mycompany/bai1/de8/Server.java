/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bai1.de8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanh
 */
public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server đang chay va cho ket noi...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Kết nối từ " + clientSocket.getRemoteSocketAddress());

                    // Nhận kích thước ma trận
                    String[] dimensions = in.readLine().split(" ");
                    int rows = Integer.parseInt(dimensions[0]);
                    int cols = Integer.parseInt(dimensions[1]);

                    // Nhận ma trận
                    List<int[]> matrix = new ArrayList<>();
                    for (int i = 0; i < rows; i++) {
                        String[] rowStr = in.readLine().split(" ");
                        int[] row = new int[cols];
                        for (int j = 0; j < cols; j++) {
                            row[j] = Integer.parseInt(rowStr[j]);
                        }
                        matrix.add(row);
                    }

                    // Xử lý ma trận để tìm phần tử lớn nhất theo từng dòng
                    StringBuilder result = new StringBuilder();
                    for (int[] row : matrix) {
                        int max = row[0];
                        for (int value : row) {
                            if (value > max) {
                                max = value;
                            }
                        }
                        result.append(max).append(" ");
                    }

                    // Gửi kết quả về cho client
                    out.println(result.toString().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}