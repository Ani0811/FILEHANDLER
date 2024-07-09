using System;
using System.IO;

namespace FileMemory
{
	class FileHandler
	{
		[STAThread]
		static void Main(string[] args)
		{
			FileMemory.FileHandler fhHandle = new FileMemory.FileHandler();
			FileInfo fiFileHandleFrom       = new FileInfo("c:\\powers_report.pdf");
			FileInfo fiFileHandleTo         = new FileInfo("c:\\powers_report_new.pdf");
			FileStream fsStreamToRead       = fiFileHandleFrom.OpenRead();
			byte[] bBytesToStream           = fhHandle.collateBytes(fsStreamToRead);
			FileStream fsStreamToWrite      = fiFileHandleTo.OpenWrite();
			fsStreamToWrite.Write(bBytesToStream, 0, bBytesToStream.Length);
			fsStreamToWrite.Close();
			fsStreamToRead.Close();
		}

		public byte[] collateBytes( Stream stInputStream )
		{
			int iBytesRead  = 0;
			StreamReader srReadStream = new StreamReader(stInputStream);
			long lBufferLength  = srReadStream.BaseStream.Length;
			byte[] bByteTemp  = new byte[lBufferLength];
			byte[] bFinalArray  = new byte[0];
			byte[] bTempArray  = new byte[0];
			do
			{
				iBytesRead = srReadStream.BaseStream.Read( bByteTemp, 0, bByteTemp.Length);
				bTempArray = new byte[bFinalArray.Length];
                				Buffer.BlockCopy(bFinalArray, 0, bTempArray, 0, bFinalArray.Length);
				bFinalArray = new byte[bTempArray.Length + iBytesRead];
				Buffer.BlockCopy(bTempArray, 0, bFinalArray, 0, bTempArray.Length);
				Buffer.BlockCopy(bByteTemp, 0, bFinalArray, bTempArray.Length,iBytesRead);
			}
			while(iBytesRead > 0);
			return bFinalArray;
		}
	}
}