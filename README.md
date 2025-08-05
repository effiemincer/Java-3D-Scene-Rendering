# Java 3D Scene Rendering Engine

A physics-based 3D rendering engine implemented in Java using ray tracing, custom scene descriptions, and multithreaded optimization.

---

## 🧠 Project Overview

This engine parses structured scene files to construct a 3D environment and renders realistic images using recursive **ray tracing**. It supports:
- **Reflection**
- **Lighting and shadows**
- **Basic material models**
- **Object composition via XML-like scene files**

Rendering is parallelized using Java’s multithreading features to improve performance on modern hardware.

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **Core Concepts:** Ray tracing, recursive reflection, camera projection
- **Optimization:** Thread pools and pixel-based work partitioning
- **Input format:** Custom `.xml`-like scene description

---

## 🚀 Features

- ✅ Scene file parser to define camera, geometries, lights, and materials
- ✅ Ray-object intersection system
- ✅ Recursive ray tracing with reflective surfaces
- ✅ Point and directional lighting with soft shadows
- ✅ Multithreaded rendering pipeline
- ✅ Exports output as `.png` image

---

## 🧑‍💻 Authors
**Effie Mincer**
Computer Science @ JCT | [effiemincer.dev](https://www.effiemincer.dev)

**Yehuda Gurovich**
Computer Science @ JCT

---

## 🧱 Project Structure

```bash
Java-3D-Scene-Rendering/
├── src/
│   ├── geometries/      # Spheres, planes, triangles, etc.
│   ├── lighting/        # Light sources
│   ├── renderer/        # Core rendering loop
│   ├── scene/           # Scene construction and parsing
│   └── main/            # Application entry point
├── scenes/              # Sample scene description files
├── output/              # Rendered image output
└── README.md
