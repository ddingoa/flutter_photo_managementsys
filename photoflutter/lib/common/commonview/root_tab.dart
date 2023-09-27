
import 'package:flutter/material.dart';
import 'package:photoflutter/photo/screen/photo_home_screen.dart';

import '../const/colors.dart';
import '../layout/default_layout.dart';

class RootTab extends StatefulWidget {
  const RootTab({super.key});

  @override
  State<RootTab> createState() => _RootTabState();
}

class _RootTabState extends State<RootTab> with SingleTickerProviderStateMixin{
  late TabController controller;
  int index =0;

  @override
  void initState(){
    super.initState();
    controller = TabController(length: 4, vsync: this);
    controller.addListener(tabListener);
  }

  @override
  void dispose(){
    controller.removeListener(tabListener);

    super.dispose();
  }

  void tabListener(){
    setState(() {
      index = controller.index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return DefaultLayout(
      title: 'PhotoSave',
      child: TabBarView(
        physics: NeverScrollableScrollPhysics() ,
        controller: controller,
        children: [
          Center(
              child: Container(
                child: Text('홈'),
              )),
          HomeScreen(),
          Center(
              child: Container(
                child: Text('업로드 현황'),
              )),
          Center(
              child: Container(
                child: Text('프로필'),
              )),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        selectedItemColor: PRIMARY_COLOR,
        unselectedItemColor: BODY_TEXT_COLOR,
        selectedFontSize: 10.0,
        unselectedFontSize: 10.0,
        type: BottomNavigationBarType.fixed,
        onTap: (int index){
          controller.animateTo(index);
        },
        currentIndex: index,
        items: [
          BottomNavigationBarItem(icon: Icon(Icons.home_outlined), label: '홈'),
          BottomNavigationBarItem(
              icon: Icon(Icons.picture_in_picture_alt_outlined), label: '사진첩'),
          BottomNavigationBarItem(
              icon: Icon(Icons.upload_file_outlined), label: '업로드 현황'),
          BottomNavigationBarItem(
              icon: Icon(Icons.person_outline), label: '프로필'),
        ],
      ),
    );
  }
}
