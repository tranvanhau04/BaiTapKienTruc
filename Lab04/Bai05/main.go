package main

import (
	"fmt"
	"net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Hello, Docker Go! Day la ung dung Golang sieu nhe.")
}

func main() {
	http.HandleFunc("/", helloHandler)
	fmt.Println("Server Go dang chay tren cong 8080...")
	// Lắng nghe trên mọi IP ở cổng 8080
	http.ListenAndServe(":8080", nil)
}