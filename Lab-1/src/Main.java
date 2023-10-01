import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;
import java.text.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scanner in = new Scanner(System.in);
        int k;
        double x;
        System.out.print("Введите погрешность k: ");             //Input/output for row without BigValue types
        if(in.hasNextInt())
        {
            k = in.nextInt();
            if (k <= 0)
            {
                System.out.println("Введено неверное число");
                return;
            }
        }
        else
        {
            System.out.println("Введено неверное число");
            return;
        }
        System.out.print("Введите значение x: ");
        if(in.hasNextDouble())
        {
            x = in.nextDouble();
        }
        else
        {
            System.out.println("Введено неверное число");
            return;
        }
        NumberFormat formatter = NumberFormat.getNumberInstance();  //Different formats, with using different methods
        formatter.setMaximumFractionDigits(k + 1);
        Row row = new Row(k, x);
        System.out.println("Row value: " + formatter.format(row.Calculate()));
        System.out.println("Function value: " + formatter.format((Math.sin(x) / x)) + "\n");
        System.out.printf("Row value in hexagonal format: %a\n", row.Calculate());
        System.out.printf("Round row value * 1000 in octal format: %o\n", Math.round(row.Calculate() * 1000));
        System.out.printf("Row value format with %%: %+09.5f\n", row.Calculate());
        System.out.printf("Row value format with 0/#: " + new DecimalFormat( "000.###" ).format(row.Calculate()) + "\n\n");
        BigInteger big_k;
        BigDecimal big_x;

        System.out.print("Введите погрешность k: ");        //Input/output for row with BigValue types
        if(in.hasNextLong())
        {
            big_k = BigInteger.valueOf(in.nextLong());
            if (big_k.compareTo(BigInteger.valueOf(0)) <= 0)
            {
                System.out.println("Введено неверное число");
                return;
            }
        }
        else
        {
            System.out.println("Введено неверное число");
            return;
        }
        System.out.print("Введите значение x: ");
        if(in.hasNextDouble())
        {
            big_x = BigDecimal.valueOf(in.nextDouble());
        }
        else
        {
            System.out.println("Введено неверное число");
            return;
        }
        formatter.setMaximumFractionDigits(big_k.intValue() + 1);
        BigRow big_row = new BigRow(big_k, big_x);
        System.out.println("Big row value: " + formatter.format(big_row.Calculate(big_k.intValue())));
        System.out.println("Function value: " + formatter.format((Math.sin(big_x.doubleValue()) / big_x.doubleValue())));
    }
}

class Row   //Row without big values
{
    double k_;		//порядок
    double x_;		//переменная
    Row(int k, double x)
    {
        k_ = 1d / Math.pow(10, k);
        x_ = x;
    }
    public double Calculate()
    {
        double sum = 0, mem = 1;
        long iter = 0;
        while (Math.abs(mem) >= k_)
        {
            sum += mem;
            iter += 2;
            mem *= x_;
            mem /= iter;
            mem *= x_;
            mem /= iter + 1;
            mem = -mem;
            if(iter > 1e9)
            {
                System.out.println("Cлишком большое число, переполнение на итерации: " + iter / 2);
                return 0;
            }
        }
        if (Math.abs(sum) > 1)
        {
            System.out.println("Cлишком большое число, переполнение на итерации: " + iter / 2);
            return 0;
        }
        return sum;
    }
}

class BigRow    //Row with big values
{
    BigDecimal k_;		//порядок
    BigDecimal x_;		//переменная
    BigRow(BigInteger k, BigDecimal x)
    {
        k_ = BigDecimal.valueOf(0.1d);
        k_ = k_.pow(k.intValue());
        x_ = x;
    }
    public BigDecimal Calculate(int k)
    {
        BigDecimal sum = BigDecimal.valueOf(0), mem = BigDecimal.valueOf(1);
        BigDecimal fact = BigDecimal.valueOf(1), pow = BigDecimal.valueOf(1);
        long iter = 0;
        int sign = -1;
        while (mem.abs().compareTo(k_) >= 0)
        {
            sum = sum.add(mem);
            iter += 2;
            fact = fact.multiply(BigDecimal.valueOf(iter));
            fact = fact.multiply(BigDecimal.valueOf(iter + 1));
            pow = pow.multiply(x_);
            pow = pow.multiply(x_);
            mem = pow.divide(fact, k + 3, RoundingMode.HALF_UP);
            mem = mem.multiply(BigDecimal.valueOf(sign));
            sign = -sign;
        }
        if (sum.abs().compareTo(BigDecimal.valueOf(1)) > 0)
        {
            System.out.println("Cлишком большое число, переполнение на итерации: " + iter / 2);
            return BigDecimal.valueOf(0);
        }
        return sum;
    }
}