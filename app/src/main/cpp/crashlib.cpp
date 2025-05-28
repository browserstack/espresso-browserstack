#include <jni.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_sample_browserstack_samplecalculator_MainActivity_nativeCrash(
        JNIEnv* /* env */, jobject /* thiz */) {
// Force a write to address zero → produces SIGSEGV
volatile int* bad = nullptr;
*bad = 0;
}
