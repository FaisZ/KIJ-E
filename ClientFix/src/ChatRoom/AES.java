/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatRoom;
import java.math.*;
import java.nio.charset.Charset;
import java.lang.Object;
import java.util.Arrays;
/**
 *
 * @author Ahmad Mustofa
 */
public class AES {
    private String initv= "aefjnostvzAEGIMO";    
    private char[][] word = new char[44][4];
    private char[][] sbox = 
    {
        {0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76},
        {0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0},
        {0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15},
        {0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75},
        {0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84},
        {0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF},
        {0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8},
        {0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2},
        {0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73},
        {0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB},
        {0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79},
        {0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08},
        {0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A},
        {0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E},
        {0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF},
        {0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16}
    };
    private char[][] inversSbox = 
    {
        {0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76},
        {0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0},
        {0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15},
        {0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75},
        {0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84},
        {0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF},
        {0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8},
        {0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2},
        {0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73},
        {0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB},
        {0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79},
        {0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08},
        {0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A},
        {0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E},
        {0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF},
        {0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16}
    };
    public String toHex(String arg) 
    {
        return String.format("%2x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }
    public char[][] InputState(String iv)
    {
        char[] tmp = iv.toCharArray();
        char[][]out = new char[4][4];
        int x;
        for(int i = 0;i<4;i++)
        {
            x = i;
            for(int j = 0;j<4;j++)
            {
                out[i][j] = tmp[x];
                x+=4;
            }
        }
        return out;
    }
    private char[][] StateBox(char[][] InputState, String status)
    {
        String tmp,x,y;
        char[] temp;
        char[][] box = new char[16][16];
        if(status.equalsIgnoreCase("enkripsi"))
            box = sbox.clone();
        else if(status.equalsIgnoreCase("dekripsi"))
            box = inversSbox.clone();
        for(int i = 0;i<4;i++)
        {
            for(int j = 0;j<4;j++)
            {
                tmp = String.format("%2x",(int)InputState[i][j]);
                if((int)InputState[i][j]<16)
                    x = "0";
                else
                    x = tmp.substring(0,1);
                //System.out.println("x = "+x);
                y = tmp.substring(1,2);
                //System.out.println("y = "+y);
                InputState[i][j] = box[Integer.valueOf(x, 16)][Integer.valueOf(y,16)];
            }
        }
        return InputState;
    }
    private char[][] ShiftRow(char[][] stateBox,String status)
    {
        char[][] out = new char[4][4];
        for(int i = 0; i < 4;i++)
        {
            System.arraycopy(stateBox[i], 0, out[i], 0, stateBox.length);
        }
        if(status.equalsIgnoreCase("enkripsi"))
        {
            stateBox[1][0] = out[1][1];
            stateBox[1][1] = out[1][2];
            stateBox[1][2] = out[1][3];
            stateBox[1][3] = out[1][0];
            stateBox[2][0] = out[2][2];
            stateBox[2][1] = out[2][3];
            stateBox[2][2] = out[2][0];
            stateBox[2][3] = out[2][1];
            stateBox[3][0] = out[3][3];
            stateBox[3][1] = out[3][0];
            stateBox[3][2] = out[3][1];
            stateBox[3][3] = out[3][2];
        }
        else if(status.equalsIgnoreCase("dekripsi"))
        {
            stateBox[1][0] = out[1][3];
            stateBox[1][1] = out[1][0];
            stateBox[1][2] = out[1][1];
            stateBox[1][3] = out[1][2];
            stateBox[2][0] = out[2][2];
            stateBox[2][1] = out[2][3];
            stateBox[2][2] = out[2][0];
            stateBox[2][3] = out[2][1];
            stateBox[3][0] = out[3][1];
            stateBox[3][1] = out[3][2];
            stateBox[3][2] = out[3][3];
            stateBox[3][3] = out[3][0];
        }
        return stateBox;
    }
    private char GaloisField(int x,char y)
    {
        int tmp;
        String biner = Integer.toBinaryString((int)y);
        if(biner.length()<8)
            biner = '0'+biner;
        char pertama = biner.charAt(0);
        if(x == 2)
        {
            biner = biner.substring(1,biner.length())+'0';
            if(pertama == '1')
            {
                tmp = Integer.parseInt(biner, 2);
                tmp = tmp^27;
                return (char)tmp;
            }
            return (char)Integer.parseInt(biner,2);
        }
        else if(x == 3)
        {
            y = (char)(y^GaloisField(2,y));
        }
        return y;
    }
    private char[][] MixColumn(char[][] shiftRow,String status)
    {
        char[][] out = new char[4][4];
        for(int i = 0; i < 4;i++)
        {
            System.arraycopy(shiftRow[i], 0, out[i], 0, shiftRow.length);
        }
        if(status.equalsIgnoreCase("enkripsi"))
        {
            for(int i = 0; i < 4;i++)
            {
                for(int j = 0; j < 4;j++)
                {
                    if(i == 0)
                    {
                        out[i][j] = (char)((int)GaloisField(2,shiftRow[0][j])^(int)GaloisField(3,shiftRow[1][j])^(int)shiftRow[2][j]^(int)shiftRow[3][j]);
                    }
                    else if(i==1)
                    {
                        out[i][j] = (char)((int)shiftRow[0][j]^(int)GaloisField(2,shiftRow[1][j])^(int)GaloisField(3,shiftRow[2][j])^(int)shiftRow[3][j]);
                    }
                    else if(i==2)
                    {
                        out[i][j] = (char)((int)shiftRow[0][j]^(int)shiftRow[1][j]^(int)GaloisField(2,shiftRow[2][j])^(int)GaloisField(3,shiftRow[3][j]));
                    }
                    else
                    {
                        out[i][j] = (char)((int)GaloisField(3,shiftRow[0][j])^(int)shiftRow[1][j]^(int)shiftRow[2][j]^(int)GaloisField(2,shiftRow[3][j]));
                    }
                }
            }
        }
        else if(status.equalsIgnoreCase("dekripsi"))
        {
            
        }
        return out;
    }
    private void ExpansionKey(char[][] key)
    {
        char[] tmp = new char[4];
        String temp;
        for(int i = 0;i<4;i++)
        {
            for(int j = 0;j<4;j++)
                word[i][j] = key[j][i];
        }
        for(int i = 4;i<44;i++)
        {
            System.arraycopy(word[i-1], 0, tmp, 0, 4);
            if(i%4==0)
            {
                temp = Arrays.toString(word[i-1]);
                temp = temp.substring(1,temp.length()) + temp.charAt(0);
                tmp = temp.toCharArray();
                temp = toHex(temp);
                char[] tmp1 = temp.toCharArray();
                int x = 0;
                for(int j = 0; j < 4;j++)
                {
                    tmp[j] = sbox[Integer.valueOf(Character.toString(tmp1[x++]),16 )][Integer.valueOf(Character.toString(tmp1[x++]),16 )];
                    if(j == 0)
                        tmp[j] = (char)((int)tmp[j]^(i/4));
                }
            }
            for(int j = 0; j < 4;j++)
                tmp[j] = (char)((int)tmp[j]^(int)word[i-4][j]);
            System.arraycopy(tmp, 0, word[i], 0, 4);
        }
        /*for(int i = 0;i<44;i++)
        {
            for(int j =0;j<4;j++)
                //System.out.print(Integer.toHexString((int)word[i][j])+" ");
                System.out.print(word[i][j]+" ");
            System.out.println();
        }*/
    }
    private char[][] AddRoundKey(char[][] mixColumn,char[][] key)
    {
        for(int i = 0;i<4;i++)
            for(int j =0;j<4;j++)
            {
                mixColumn[i][j] = (char)((int)mixColumn[i][j]^(int)key[i][j]);
            }
        return mixColumn;
    }
    public String AesStart(String pesan,String key,String status)
    {
        int modulo = pesan.length()%16,index;
        String hasilAkhir = "";
        char[][] hasil = new char[4][4],kunci = new char[4][4];
        if(modulo != 0)
        {
           String padding = "";
           for(int i = modulo;i<16;i++)
               padding = padding + "0";
           pesan = pesan+padding;
           System.out.println(pesan.length());
        }
        modulo = pesan.length()/16;
        ExpansionKey(InputState(key));
        String[] input = new String[modulo],output = new String[modulo];
        char[] tmp;
        String iv = initv;
        for(int i = 0;i<modulo;i++)
        {
            input[i] = pesan.substring(16*i,16*i+16);
            tmp = input[i].toCharArray();
            if(i != 0)
            {
                if(status.equalsIgnoreCase("dekripsi"))
                    iv = input[i-1];
                else if(status.equalsIgnoreCase("enkripsi"))
                    iv = output[i-1];
            }
            for(int j = 0;j<10;j++)
            {
                index = (j+1)*4;
                for(int x = 0;x<4;x++)
                {
                    for(int y = 0;y<4;y++)
                    {
                        kunci[x][y] = word[index+y][x];
                    }
                }
                if(j == 0)
                    hasil = InputState(iv);
                /////
                hasil = StateBox(hasil,"enkripsi");
                /////
                hasil = ShiftRow(hasil,"enkripsi");
                /////////
                if(j != 9)
                    hasil = MixColumn(hasil,"enkripsi");
                hasil = AddRoundKey(hasil,kunci);
            }
            /*if(i == 0)
            {
                for(int x = 0;x<4;x++)
                {
                    for(int y = 0;y<4;y++)
                    {
                        System.out.print(hasil[x][y]);
                    }
                }
                System.out.println();
            }*/
            index = 0;
            for(int x = 0;x<4;x++)
            {
                for(int y = 0;y<4;y++)
                {
                    tmp[index] = (char)((int)tmp[index]^(int)hasil[y][x]);
                    //System.out.print(tmp[index]);
                    index++;
                }
            }
            //System.out.println();
            output[i] = new String(tmp);
            //System.out.println(subPesan[i]);
        }
        for(int i = 0;i<modulo;i++)
            hasilAkhir = hasilAkhir+output[i];
        return hasilAkhir;
    }
    /*public static void main(String[] args)
    {
        String pesan = "abcdefghijklmnopabcdefghijklmnopabcdefghijklmnop",key = "qfwqhoihrqoiafdc";
        String enkripsi = AesStart(pesan,key,"enkripsi");
        String dekripsi = AesStart(enkripsi,key,"dekripsi");
        System.out.println(pesan);
        System.out.println(enkripsi);
        System.out.println(dekripsi);
        /*char[][] out = InputState(pesan).clone();
        for(int i = 0; i < 4;i++)
        {
            for(int j = 0; j < 4;j++)
                System.out.print(out[i][j]+" ");
            System.out.println();
        }
        out = StateBox(out,"enkripsi");
        for(int i = 0; i < 4;i++)
        {
            for(int j = 0; j < 4;j++)
            {
                System.out.println(out[i][j]);
            }
            System.out.println();
        }
        out = ShiftRow(out,"enkripsi");
        for(int i = 0; i < 4;i++)
        {
            for(int j = 0; j < 4;j++)
            {
                System.out.println(out[i][j]);
            }
            System.out.println();
        }
        out = MixColumn(out,"enkripsi");
        for(int i = 0; i < 4;i++)
        {
            for(int j = 0; j < 4;j++)
            {
                System.out.println(out[i][j]);
            }
            System.out.println();
        }
        //char m = GaloisField(2,'a');
        //biner = biner.substring(1,biner.length())+'0';
        //System.out.println(m);
    }*/
}