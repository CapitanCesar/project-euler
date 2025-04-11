package com.example.demo.business;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.example.demo.classes.Complex;
import com.example.demo.records.Euler;

public class EulerSecret {
	public class FFT2D {
		public static void fft2D(Complex[][] input, boolean forward) {
			int h = input.length;
			int w = input[0].length;

			// FFT rows
			for (int y = 0; y < h; y++) {
				input[y] = fft1D(input[y], forward);
			}

			// FFT columns
			for (int x = 0; x < w; x++) {
				Complex[] column = new Complex[h];
				for (int y = 0; y < h; y++) {
					column[y] = input[y][x];
				}
				column = fft1D(column, forward);
				for (int y = 0; y < h; y++) {
					input[y][x] = column[y];
				}
			}

			// Normalize if inverse
			if (!forward) {
				for (int y = 0; y < h; y++) {
					for (int x = 0; x < w; x++) {
						input[y][x] = input[y][x].scale(1.0 / (h * w));
					}
				}
			}
		}

		private static Complex[] fft1D(Complex[] x, boolean forward) {
			int n = x.length;
			if (n == 1) {
				return new Complex[] { x[0] };
			}

			if ((n & (n - 1)) != 0) {
				throw new IllegalArgumentException("Array length is not a power of 2");
			}

			Complex[] even = new Complex[n / 2];
			Complex[] odd = new Complex[n / 2];
			for (int k = 0; k < n / 2; k++) {
				even[k] = x[2 * k];
				odd[k] = x[2 * k + 1];
			}

			Complex[] q = fft1D(even, forward);
			Complex[] r = fft1D(odd, forward);

			Complex[] y = new Complex[n];
			double sign = forward ? -1 : 1;
			for (int k = 0; k < n / 2; k++) {
				double angle = 2 * Math.PI * k / n * sign;
				Complex wk = new Complex(Math.cos(angle), Math.sin(angle));
				y[k] = q[k].plus(wk.times(r[k]));
				y[k + n / 2] = q[k].minus(wk.times(r[k]));
			}

			return y;
		}
	}

	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>.</p>";
		String solution = "";

		try {
			if (isTest) {
				solveProblem("bonus_secret_statement.png");				
			} else {
				solveProblem("bonus_secret_padded.png");				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Euler(problem, solution);
	}

	public static void solveProblem(String filename) throws IOException {
		// Load image
		BufferedImage img = ImageIO.read(new File(filename));
		int width = img.getWidth();
		int height = img.getHeight();

		// Store initial grayscale values
		long[][] current = new long[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int rgb = img.getRGB(x, y);
				int gray = rgb & 0xFF; // extract blue channel (assumes grayscale image)
				current[y][x] = gray;
				current[y][x] = rgb;
			}
		}

		// Precompute kernel effect using fast matrix exponentiation in the frequency domain
//		long steps = (long) 1e12;
//		long[][] result = convolveTorus(current, steps);
		long[][] result = current;

		// Convert result modulo 7 and print
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				System.out.print(result[y][x] % 7);
//			}
//			System.out.println();
//		}

		// Create new image with result modulo 7 scaled for visibility (e.g., value * 36)
		BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int val = (int) (result[y][x] % 7);
				int gray = val * 255 / 6; // Scale 0–6 to 0–255
				int rgb = (gray << 16) | (gray << 8) | gray; // grayscale
				outputImage.setRGB(x, y, rgb);
			}
		}

		// Save the image
		filename = "bonus_secret_output.png";
		ImageIO.write(outputImage, "png", new File(filename));
		System.out.printf("Image saved as %s\n", filename);

	}

	// Apply the transformation using fast exponentiation in toroidal space
	static long[][] convolveTorus(long[][] data, long steps) {
		int h = data.length;
		int w = data[0].length;
		long[][] result = new long[h][w];

		// Initialize frequency matrix
		Complex[][] freq = new Complex[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				freq[y][x] = new Complex(data[y][x], 0);
			}
		}

		// FFT
		FFT2D.fft2D(freq, true);

		// Compute eigenvalue for each frequency component
		for (int y = 0; y < h; y++) {
			double ky = 2 * Math.PI * y / h;
			for (int x = 0; x < w; x++) {
				double kx = 2 * Math.PI * x / w;
				double eigenvalue = 2 * (Math.cos(kx) + Math.cos(ky));
				freq[y][x] = freq[y][x].scale(Math.pow(eigenvalue, steps));
			}
		}

		// Inverse FFT
		FFT2D.fft2D(freq, false);

		// Store result (real part only)
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				result[y][x] = Math.round(freq[y][x].re());
			}
		}

		return result;
	}
}
