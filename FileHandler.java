import java.io.*;
public class FileHandler
{
    public static void main(String[] args)
    {
        FileHandler fhHandle = new FileHandler();
        File fiFileHandleFrom       = new File("C:\\NARAYANA\\Vivo.Y72.5G.Invoice.pdf");
        File fiFileHandleTo         = new File("C:\\NSEC\\Vivo.Invoice.New.pdf");
        try
        {
            FileInputStream fsStreamToRead = new FileInputStream(fiFileHandleFrom);

            byte[] bBytesToStream = fhHandle.collateBytes(fsStreamToRead);

            FileOutputStream fsStreamToWrite = new FileOutputStream(fiFileHandleTo);

            fsStreamToWrite.write(bBytesToStream);
            fsStreamToWrite.close();
            fsStreamToRead.close();
        }
        catch(IOException e) { e.printStackTrace(); }
    }
    public byte[] collateBytes( InputStream stInputStream ) throws IOException
    {
        int iBytesRead = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bByteTemp = new byte[(1024 * 1024) * 3];
        //byte[] bFinalArray  = new byte[0];
        //byte[] bTempArray  = new byte[0];
        outputStream.close();

        do
        {
            iBytesRead = stInputStream.read(bByteTemp, 0, bByteTemp.length);
            /*bTempArray = new byte[bFinalArray.length];
            System.arraycopy(bFinalArray, 0, bTempArray, 0, bFinalArray.length);
            bFinalArray = new byte[bTempArray.length + iBytesRead];
            System.arraycopy(bTempArray, 0, bFinalArray, 0, bTempArray.length);
            System.arraycopy(bByteTemp, 0, bFinalArray, bTempArray.length, iBytesRead);/*
             */
            if(iBytesRead > 0)
            {
                outputStream.write(bByteTemp, 0, iBytesRead);
            }
        }
        while(iBytesRead > 0);
        return outputStream.toByteArray();
    }
}
