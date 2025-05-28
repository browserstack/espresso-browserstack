cmake_minimum_required(VERSION 3.10.2)
project("crashlib")

# Build a shared library called "crashlib"
add_library(crashlib SHARED crashlib.cpp)

# Link against Android's log library if you ever want to log
find_library(log-lib log)
target_link_libraries(crashlib ${log-lib})
