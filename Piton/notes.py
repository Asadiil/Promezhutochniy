import json
import datetime


class NotesApp:
    def __init__(self):
        self.notes = []

    def add_note(self, title, message):
        note = {
            "id": len(self.notes) + 1,
            "title": title,
            "message": message,
            "timestamp": datetime.datetime.now().isoformat()
        }
        self.notes.append(note)
        print("Заметка успешно сохранена")

    def read_notes(self):
        for note in self.notes:
            print(f"Заголовок: {note['title']}")
            print(f"Тело: {note['message']}")
            print(f"Дата/Время: {note['timestamp']}")
            print()
        print(f"Всего заметок: {len(self.notes)}")

    def edit_note(self, note_id, title, message):
        for note in self.notes:
            if note['id'] == note_id:
                note['title'] = title
                note['message'] = message
                note['timestamp'] = datetime.datetime.now().isoformat()
                print("Заметка успешно отредактирована")
                return
        print("Заметка с указанным идентификатором не найдена")

    def delete_note(self, note_id):
        for note in self.notes:
            if note['id'] == note_id:
                self.notes.remove(note)
                print("Заметка успешно удалена")
                return
        print("Заметка с указанным идентификатором не найдена")

    def save_notes_to_file(self, file_path):
        with open(file_path, "w") as file:
            json.dump(self.notes, file, indent=4)

    def load_notes_from_file(self, file_path):
        with open(file_path, "r") as file:
            self.notes = json.load(file)


notes_app = NotesApp()

command = input("Введите команду: ")

while command != "exit":
    if command == "add":
        title = input("Введите заголовок заметки: ")
        message = input("Введите тело заметки: ")
        notes_app.add_note(title, message)
    elif command == "read":
        notes_app.read_notes()
    elif command == "edit":
        note_id = int(input("Введите идентификатор заметки: "))
        title = input("Введите новый заголовок заметки: ")
        message = input("Введите новое тело заметки: ")
        notes_app.edit_note(note_id, title, message)
    elif command == "delete":
        note_id = int(input("Введите идентификатор заметки: "))
        notes_app.delete_note(note_id)
    else:
        print("Неверная команда")

    command = input("Введите команду: ")