int array[10];

void printNum(int a) {
	putchar(a + 48);
}

void quickSort(int start, int stop) {
	if (start < stop) {
		int pivot;
		int i;
		int j;

		i = start;
		pivot = array[stop];

		j = start;
		while (j < stop) {
			if (array[j] < pivot) {
				int temp;

				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i = i + 1;
			}
			j = j + 1;
		}

		array[stop] = array[i];
		array[i] = pivot;

		quickSort(start, i - 1);
		quickSort(i + 1, stop);
	}
}	

int main( void ) {
	int len;
	int i;
	len = getchar() - 47;
	i = 0;
	while (i < len) {
		array[i] = getchar() - 48;
		i = i + 1;
	}

	{
		int len;
		len = 0;
	}

	quickSort(0, len - 1);	

	i = 0;
	while (i < len) {
		printNum(array[i]);
		i = i + 1;
	}

	putchar(10);
}

	
