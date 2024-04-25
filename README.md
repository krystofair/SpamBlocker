# SpamBlocker
An Android Call/SMS blocker.

<img src="https://github.com/aj3423/SpamBlocker/assets/4710875/20930282-db38-4c21-a0db-4720ad666151" height="100">

# Screenshot
| Call        | Sms         | Setting     | Notification |
| ----        | ----        | ----        | ----         |
| <img src="https://github.com/aj3423/SpamBlocker/assets/4710875/193db71d-84b0-4441-8225-cfcb6827f40e" width="200"> | <img src="https://github.com/aj3423/SpamBlocker/assets/4710875/f9e3c6f3-5045-479e-99ab-e0edee701b5c" width="200"> | <img src="https://github.com/aj3423/SpamBlocker/assets/4710875/4857ad5d-96b4-47c1-9627-f33770ab399e" width="200"> | <img src="https://github.com/aj3423/SpamBlocker/assets/4710875/99b62b58-8689-41b9-bd4b-7171af316ab5" width="200">  |

# Features
|                                                    | For Call                                                                                                                                               | For Sms                                                                                                        |
| ----                                               | ----                                                                                                                                               | ----                                                                                                       |
| What it does                                       | Block unwanted calls                                                                                                                               | Silence unwanted notificaion                                                                               |
| What it doesn't                                    | Replace the default call app                                                                                                                       | Replace the default sms app                                                                                |
| How it works                                       | Act as [CallScreeningService](https://developer.android.com/reference/android/telecom/CallScreeningService),<br>aka the default caller ID & spam app | Turn off the notification of the default sms app<br>(Note: you need to turn it off manually in system settings).<br>This app takes over the notification of incoming message. |
| Filters supported<br>([explained below](#Filters)) | 1. Phone number (regex)<br>2. In Contacts<br>3. Repeated call<br>4. Recent apps                                                                     | 1. Phone number (regex)<br>2. In Contacts<br>3. Sms content (regex)                                        |



# Filters:
#### 1. Phone number and Sms content

Regex is used, ask AI if you don't know how to write one, eg: 
"Show me regex for checking if a string starts with 400 and has a length of 10", which results in `^400.{7}$`

Some typical patterns:
- Any number: `.*`
- Exact number: `12345`
- Starts with 400: `^400.*`
- Longer than 10: `.{11,}`
- Contains "verification": `.*verification.*`

#### 2. In Contacts
Permit if the number belongs to a contact.

#### 3. Repeated Call
Calls repeated within a period of time will be permitted.

#### 4. Recent Apps
Any call would be permitted if any of these apps has been used within a period of time.

- A typical use case: 

You ordered a pizza in PizzaApp, soon they call you to refund because they are closing. That call would be permitted if PizzaApp is enabled in Recent Apps list.


# Permissions required

| Permission          | Why                                                                          |
| ----                | ----                                                                         |
| READ_CALL_LOG       | For feature: Recent call (checking if a number is repeated within 5 min)     |
| ANSWER_PHONE_CALLS  | Reject spamming call                                                         |
| POST_NOTIFICATIONS  | Show notifications                                                           |
| READ_CONTACTS       | For matching contact number and showing contact avatar                       |
| RECEIVE_SMS         | For receiving new incoming message                                           |
| PACKAGE_USAGE_STATS | For feature: Recent Apps (checking whether an app has been used within 5 min) |
| QUERY_ALL_PACKAGES  | For feature: Recent Apps (listing all apps for choosing)                      |

