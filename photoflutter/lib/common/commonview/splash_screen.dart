
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:photoflutter/common/commonview/root_tab.dart';

import '../../user/view/login_screen.dart';
import '../const/colors.dart';
import '../const/data.dart';
import '../layout/default_layout.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({
    super.key});

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

  @override
  void initState(){
    super.initState();

    checkToken();
  }

  void deleteToken() async{
    await storage.deleteAll();
  }


  void checkToken() async{
    final refreshToken =  await storage.read(key: REFRESH_TOKEN_KEY);
    final accessToken = await storage.read(key: ACCESS_TOKEN_KEY);

    final dio = Dio();

    try{
      // final resp = await dio.post(
      //   'http://$ip/auth/reissue',
      //   options: Options(
      //     headers: {
      //       'ddingoToken': 'DDingo $refreshToken',
      //     },
      //   ),
      // );

      final resp = await dio.post(
        'http://$ip/auth/reissue',
          options: Options(
            headers: {
              'ddingoToken': 'DDingo $accessToken',
            },
          ),
         data: {'accessToken': accessToken, 'refreshToken': refreshToken},
      );

      await storage.write(key: ACCESS_TOKEN_KEY, value: resp.data['accessToken']);
      await storage.write(key: REFRESH_TOKEN_KEY, value: resp.data['refreshToken']);

      Navigator.of(context).pushAndRemoveUntil(
        MaterialPageRoute(builder: (_) => RootTab(),
        ),
            (route) => false,
      );

    }catch(e){
      Navigator.of(context).pushAndRemoveUntil(
        MaterialPageRoute(builder: (_) => LoginScreen(),
        ),
            (route) => false,
      );
    }

  }


  @override
  Widget build(BuildContext context) {
    return DefaultLayout(
      backgroundColor: PRIMARY_COLOR,
      child: SizedBox(
        width: MediaQuery.of(context).size.width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.asset(
              'asset/img/logo.png',
              width: MediaQuery.of(context).size.width / 2,
            ),
            const SizedBox(
              height: 16.0,
            ),
            CircularProgressIndicator(
              color: Colors.white,
            ),
          ],
        ),
      ),
    );
  }
}
