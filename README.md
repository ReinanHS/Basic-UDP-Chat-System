<p align="center">
 <img width="300px" height="300px" src="https://github.com/ReinanHS/Basic-UDP-Chat-System/blob/main/.github/docs/assets/logo-project.png" title="Logo"/>
</p>


# Basic UDP Chat System

A simple chat system that uses the network to connect one chat client to another chat client, allowing each client to communicate over the connection.



## Extra information

This project was a request made in the distributed systems discipline for students to write, in the programming language they prefer, an application with the following requirements:

- [ ] When running on two computers, it allows communication between two partners to exchange simple messages.
- [ ] Only use UDP communication between partners.
- [ ] Allow either side to end the communication.
- [ ] The received message must be identified with the name of the sender and the IP of the origin. *Example*:

   ```sh
   Message received from 192.168.10.22 | Maria: Hello, Pedro!
   ```

- [ ] The prompt for typing messages should display the user login and the IP of the computer where the software is running. Example:

  ```sh
  192.168.10.10 | Pedro:
  ```

Listed below is some information about the activity and the teacher who requests the implementation of this project:

- Teacher: [Wilhelm de Araujo Rodrigues](https://sig.ifs.edu.br/sigaa/public/docente/portal.jsf?siape=1843549)
- Subject: Distributed systems
- Course: Bachelor of Information Systems

## Usage/Examples

```ssh
java udpChat.java 192.168.10.10 reinan
system: connection established successfully
system: enter a message: Hello 
reinan@192.168.10.10: Hello
lucas@192.168.10.11: Hi, Reinan
```


## Authors

- [@reinanhs](https://www.github.com/reinanhs)
- [@edudevinfo](https://github.com/edudevinfo)



## License

[MIT](https://choosealicense.com/licenses/mit/)


