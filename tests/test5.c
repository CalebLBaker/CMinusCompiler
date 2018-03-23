int add(int a, int b) {
	return a + b;
}

int main(void) {
	int x;
	x = 5 + 17;
	if (x > 12) {
		int y;
		y = x - 13;
	}
	while (x > y) {
		y = y + 1;
	}
	return 0;
}
