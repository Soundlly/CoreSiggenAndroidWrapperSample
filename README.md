# Soundlly Siggen for Android

> Soundlly Siggen SDK module allows you to generate an inaudible Soundlly signal by configuring the signal ID, signal volume level and sampling rate. This document aims to provide a simple guide about how to get started with the Soundlly Siggen SDK module. (written by Soonwon Ka, 4th Sep., 2018)

## Getting Started

1. Import the `.aar` file into Android project

2. Import the `siggen` package in the target java source code
   ```
   package io.bitsound.siggen;
   ```

3. Load the native library and create the siggenInterface instance

   ```
   RuntimeHelper.loadNativeLibrary(context, "siggen");
   SiggenInterface siggen = new SiggenInterface();
   ```

4. Init the `siggen` module

   ```
   siggen.init(type, samplingRate);
   ```

   > Currently, `type`=`13` is only supported. (2018. 09. 04.)

   > `samplingRate` is supported for `44100` or `48000`. (2018. 09. 04.)

5. Set the buffer for a single signal
   ```
    int bufferSize = siggen.getPacketSize();
    short[] buffer = new short[bufferSize];
   ```

6. Load the signal to the defined buffer
   ```
    siggen.generateSignal(id, buffer, volume);
   ```

   > `id` is the integer value of signal ID

   > `volume` is the digital volume level of the generated signal. The units are dBFs, and the default value is `-30`. The adequate value can vary depending on which smartphone device the program operates on.


7. Release the `siggen` module

   ```
   siggen.release();
   ```

## Example Code

Please check out the provided test code `SiggenInterfaceTest.java`.

## APIs

| Method                                                       | Function                                                     | Details                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ | -------------------------------------- |
| *public native boolean* init(*int* type, *int* sampleRate);  | Create the c++ siggen instance with `type` and `samplingRate` configured | `type` is currently supported for `13` |
| *public native int* getPacketSize();                         | Return the buffer size of a single Soundlly signal           |                                        |
| *public native int* generateSignal(*long* soundllyId, *short[]* buffer, *int* volume); | Generate and return the a single Soundlly signal             |                                        |
| *public native boolean* release();                           | Release the c++ siggen instance                              |                                        |

- Return code

| Case              | Integer return code | Content                                                      |
| ----------------- | ------------------- | ------------------------------------------------------------ |
| SUCCESS           | 0                   |                                                              |
| ERROR             | -1                  | This code is returned if `siggen` instance's pointer is `NULL` in c++ library |
| ERROR_SAMPLE_RATE | -4                  | This code is returned if the configured sampling rate is not supported. The sampling rate supports only `44100` and `48000` |
| ERROR_VOLUME      | -5                  | This code is returned if the configured volume is out of range. The allowed signal volume level (dBFs) ranges from `-32` to `0` |
