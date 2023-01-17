import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'package:quizz_app/screens/main_menu.dart';

import 'package:quizz_app/screens/getRandom.dart';
// import 'package:quizz_app/screens/getTest.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const MainMenu(),
      // home: const MyHomePage(),
      // routes: {'/second': (context) => const MyHomePage('a')}
    );
  }
}
