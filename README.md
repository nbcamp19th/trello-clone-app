#  Spring Trello 프로젝트

## 📖 프로젝트 소개
내일배움캠프를 진행하면서 배웠던 Spring에 대한 모든 기능을 적극 활용하여 Trello를 구현합니다.

## ⌚ 프로젝트 기간
* 24.10.14 - 24.10.17

## 📚 기술 스택
### 📋 Languages
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![YAML](https://img.shields.io/badge/yaml-%23ffffff.svg?style=for-the-badge&logo=yaml&logoColor=151515)

### 💻 Editors
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

### 🎨 Design
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)

### 📚 Framework
![Spring](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20JPA-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20JWT-FBBA00?style=for-the-badge&logo=Spring&logoColor=white)
![ELK](https://img.shields.io/badge/ELK-%230377CC.svg?style=for-the-badge&)

### 💾 Databases
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)

### 💬 Communication
![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)

### 🔬 CI
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

### 🕓 Version Control
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

### ☁️ SaaS
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

### 🎛️ Operating System
![macOS](https://img.shields.io/badge/mac%20os-000000?style=for-the-badge&logo=macos&logoColor=F0F0F0)
![Windows](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)

## 🏠 멤버 구성 및 기능 구현
|                 김나람                  |                 나유화                  |                   정지윤                    |                     조준호                      |                       황호진                        |
|:------------------------------------:|:------------------------------------:|:----------------------------------------:|:--------------------------------------------:|:------------------------------------------------:|
| [@kim-na-ram](https://github.com/kim-na-ram) | [@fargoe](https://github.com/fargoe) | [@jiyumi00](https://github.com/jiyumi00) | [@JUNO0432](https://github.com/JUNO0432) | [@ballqs](https://github.com/ballqs) |

## 🤝 역할 분담
* 김나람 : Spring Security , JWT 인증/인가 , 유저 , 리스트 , CI/CD
* 나유화 : 보드 , ELK
* 정지윤 : 댓글 , 알람(SLACK BOT 활용) , SSE
* 조준호 : 카드 , 담당자 , 인덱싱
* 황호진 : 워크스페이스 , Redis 동시성 제어
  <br>

## 🚩 기능 구현

### 유저
* 회원가입
* 회원탈퇴
* 로그인
* 권한변경

### 워크스페이스
* 워크스페이스 조회
* 워크스페이스 생성
* 워크스페이스 수정
* 워크스페이스 삭제
* 워크스페이스 멤버초대

### 보드
* 보드 생성
* 보드 수정
* 보드 다건 조회
* 보드 단건 조회
* 보드 삭제

### 리스트
* 리스트 생성
* 리스트 수정
* 리스트 순서 조정
* 리스트 삭제

### 카드
* 카드 생성
* 카드 수정
* 카드 다건 조회
* 카드 상세 조회
* 카드 삭제
* 카드 상태 업데이트

### 담당자
* 매니저 등록

### 댓글
* 댓글 등록
* 댓글 수정
* 댓글 삭제

## ☁ 와이어프레임
https://www.figma.com/design/qQXpAfUTnn0wtCwxr0giZ5/team19_%ED%94%8C%EB%9F%AC%EC%8A%A4%EC%A3%BC%EC%B0%A8?node-id=0-1&node-type=canvas&t=rBTdZvIv9bdM5NKS-0

## ☁ ERD 다이어그램
![image](https://github.com/user-attachments/assets/d9367381-5f80-4a8a-9b77-db6bbf390802)

## 📑 API 명세서
![image](https://github.com/user-attachments/assets/9fad27cf-ed1d-48ce-ab2a-052c019dfa4c)
![image](https://github.com/user-attachments/assets/bb1529ab-f646-4708-be0d-6fbe4320453b)
![image](https://github.com/user-attachments/assets/c5c221f5-0497-4663-9c44-832ac2875230)
![image](https://github.com/user-attachments/assets/9caf4236-0c3c-46b2-9c95-60e3598d8bdd)
![image](https://github.com/user-attachments/assets/9d90e969-17fd-454c-89b5-dcb3b1a97a91)
![image](https://github.com/user-attachments/assets/1ed70796-c140-4d7a-bedf-714c24cd008e)